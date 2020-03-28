package com.zju.se.nohair.fitness.customer.service.impl;

import com.zju.se.nohair.fitness.commons.constant.PrivateCourseStatus;
import com.zju.se.nohair.fitness.commons.constant.PublicCourseStatus;
import com.zju.se.nohair.fitness.commons.constant.ReceiveRecordType;
import com.zju.se.nohair.fitness.commons.constant.VipCardType;
import com.zju.se.nohair.fitness.commons.constant.VipOrderStatus;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.commons.utils.DateUtils;
import com.zju.se.nohair.fitness.commons.utils.FileUtils;
import com.zju.se.nohair.fitness.customer.dao.mapper.NotifiesInCustomerMapper;
import com.zju.se.nohair.fitness.customer.dao.mapper.VipCardInCustomerMapper;
import com.zju.se.nohair.fitness.customer.dto.CommentCourseDto;
import com.zju.se.nohair.fitness.customer.dto.CustomerInfoDto;
import com.zju.se.nohair.fitness.customer.dto.LoginDto;
import com.zju.se.nohair.fitness.customer.dto.NotifiesDto;
import com.zju.se.nohair.fitness.customer.dto.PurchaseVipCardDto;
import com.zju.se.nohair.fitness.customer.dto.RechargeDto;
import com.zju.se.nohair.fitness.customer.dto.RegisterDto;
import com.zju.se.nohair.fitness.customer.dto.VipCardWithGymInfoDto;
import com.zju.se.nohair.fitness.customer.service.CustomerService;
import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.mapper.GymMapper;
import com.zju.se.nohair.fitness.dao.mapper.GymVipCardMapper;
import com.zju.se.nohair.fitness.dao.mapper.NotifiesMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.mapper.PrivateCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.PublicCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.RatesMapper;
import com.zju.se.nohair.fitness.dao.mapper.ReceiveRecordMapper;
import com.zju.se.nohair.fitness.dao.mapper.VipCardMapper;
import com.zju.se.nohair.fitness.dao.mapper.VipOrderMapper;
import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import com.zju.se.nohair.fitness.dao.po.CustomerPo;
import com.zju.se.nohair.fitness.dao.po.GymPo;
import com.zju.se.nohair.fitness.dao.po.GymVipCardPo;
import com.zju.se.nohair.fitness.dao.po.GymVipCardPoKey;
import com.zju.se.nohair.fitness.dao.po.NotifiesPo;
import com.zju.se.nohair.fitness.dao.po.NotifiesPoKey;
import com.zju.se.nohair.fitness.dao.po.PicturePo;
import com.zju.se.nohair.fitness.dao.po.PrivateCoursePo;
import com.zju.se.nohair.fitness.dao.po.PublicCoursePo;
import com.zju.se.nohair.fitness.dao.po.RatesPo;
import com.zju.se.nohair.fitness.dao.po.ReceiveRecordPo;
import com.zju.se.nohair.fitness.dao.po.TakesPublicPoKey;
import com.zju.se.nohair.fitness.dao.po.VipCardPo;
import com.zju.se.nohair.fitness.dao.po.VipOrderPo;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/28 18:12
 */
@Service
public class CustomerServiceImp implements CustomerService {

  private static Logger logger = LoggerFactory.getLogger(CustomerServiceImp.class);
  @Autowired
  private CustomerMapper customerMapper;
  @Autowired
  private VipCardInCustomerMapper vipCardMapper;
  @Autowired
  private PublicCourseMapper publicCourseMapper;
  @Autowired
  private PrivateCourseMapper privateCourseMapper;
  @Autowired
  private RatesMapper ratesMapper;
  @Autowired
  private PictureMapper pictureMapper;
  @Autowired
  private GymVipCardMapper gymVipCardMapper;
  @Autowired
  private GymMapper gymMapper;
  @Autowired
  private VipCardMapper vipCardMapperInCommons;
  @Autowired
  private VipOrderMapper vipOrderMapper;
  @Autowired
  private ReceiveRecordMapper receiveRecordMapper;
  @Autowired
  private NotifiesMapper notifiesMapper;
  @Autowired
  private NotifiesInCustomerMapper notifiesInCustomerMapper;

  @Override
  public BaseResult getCustomerBalance(Integer customerId) {
    BaseResult res = null;

    try {

      CustomerPo customerPo = customerMapper.selectByPrimaryKey(customerId);
      res = BaseResult.success("查询成功");
      res.setMessage(customerPo.getBalance().toString());

    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询失败");
    }

    return res;
  }

  @Override
  public BaseResult getCustomerVipCardInfo(Integer customerId) {
    BaseResult res = null;

    try {

      List<VipCardWithGymInfoDto> vipCards = vipCardMapper.selectByCustomerId(customerId);
      for(VipCardWithGymInfoDto v:vipCards){
        Date start = v.getStartTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        changeToZeroOfDay(calendar);
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        changeToZeroOfDay(now);
        boolean available = false;
        int differenceDays = differentDays(calendar,now);
        if (v.getType()== VipCardType.MONTHLY){
          available = (differenceDays>30)?false:true;
          calendar.add(Calendar.DATE,30);
        }else if (v.getType()== VipCardType.SESSIONLY){
          available = (differenceDays>90)?false:true;
          calendar.add(Calendar.DATE,90);
        }else if (v.getType()== VipCardType.YEARLY){
          available = (differenceDays>365)?false:true;
          calendar.add(Calendar.DATE,365);
        }
        v.setEndTime(calendar.getTime());
        v.setAvailableOrNot(available);
      }
      res = BaseResult.success("查询成功");
      res.setData(vipCards);

    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询失败");
    }

    return res;
  }

  @Override
  public BaseResult createCommentForPublicCourse(CommentCourseDto commentCourseDto) {
    BaseResult res = null;

    try {

      PublicCoursePo publicCoursePo = publicCourseMapper.selectByPrimaryKey(commentCourseDto.getCourseId());
      if (publicCoursePo.getStatus() == PublicCourseStatus.END_WITHOUT_EVALUATION){
        PublicCoursePo temp = new PublicCoursePo();
        temp.setId(commentCourseDto.getCourseId());
        temp.setStatus(PublicCourseStatus.EVALUATED);
        publicCourseMapper.updateByPrimaryKeySelective(temp);
      }

      Date now = new Date();

      RatesPo ratesPo = new RatesPo();
      ratesPo.setFromId(commentCourseDto.getCustomerId());
      ratesPo.setToId(commentCourseDto.getCoachId());
      ratesPo.setContent(commentCourseDto.getCoachContent());
      ratesPo.setCourseId(commentCourseDto.getCourseId());
      ratesPo.setRatingPoints(commentCourseDto.getCoachRating());
      ratesPo.setType(3);
      ratesPo.setTime(now);
      ratesMapper.insert(ratesPo);

      ratesPo.setFromId(commentCourseDto.getCustomerId());
      ratesPo.setToId(commentCourseDto.getGymId());
      ratesPo.setContent(commentCourseDto.getGymContent());
      ratesPo.setCourseId(commentCourseDto.getCourseId());
      ratesPo.setRatingPoints(commentCourseDto.getGymRating());
      ratesPo.setType(5);
      ratesPo.setTime(new Date(now.getTime()+1000));
      ratesMapper.insert(ratesPo);

      res = BaseResult.success("评论成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("评论失败");
    }

    return res;
  }


  @Override
  public BaseResult createCommentForPrivateCourse(CommentCourseDto commentCourseDto) {
    BaseResult res = null;

    try {

      PrivateCoursePo privateCoursePo = privateCourseMapper.selectByPrimaryKey(commentCourseDto.getCourseId());
      if (privateCoursePo.getStatus() == PrivateCourseStatus.END_WITHOUT_EVALUATION){
        PrivateCoursePo temp = new PrivateCoursePo();
        temp.setId(commentCourseDto.getCourseId());
        temp.setStatus(PrivateCourseStatus.EVALUATED);
        privateCourseMapper.updateByPrimaryKeySelective(temp);
      }

      Date now = new Date();

      RatesPo ratesPo = new RatesPo();
      ratesPo.setFromId(commentCourseDto.getCustomerId());
      ratesPo.setToId(commentCourseDto.getCoachId());
      ratesPo.setContent(commentCourseDto.getCoachContent());
      ratesPo.setCourseId(commentCourseDto.getCourseId());
      ratesPo.setRatingPoints(commentCourseDto.getCoachRating());
      ratesPo.setType(9);
      ratesPo.setTime(now);
      ratesMapper.insert(ratesPo);

      ratesPo.setFromId(commentCourseDto.getCustomerId());
      ratesPo.setToId(commentCourseDto.getGymId());
      ratesPo.setContent(commentCourseDto.getGymContent());
      ratesPo.setCourseId(commentCourseDto.getCourseId());
      ratesPo.setRatingPoints(commentCourseDto.getGymRating());
      ratesPo.setType(11);
      ratesPo.setTime(new Date(now.getTime()+1000));
      ratesMapper.insert(ratesPo);

      res = BaseResult.success("评论成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("评论失败");
    }

    return res;
  }

  @Override
  public BaseResult getCustomerInfo(Integer customerId) {
    BaseResult res = null;

    try {

      CustomerInfoDto customerInfoDto = new CustomerInfoDto();
      CustomerPo customerPo = customerMapper.selectByPrimaryKey(customerId);
      BeanUtils.copyProperties(customerPo,customerInfoDto);
      if (customerPo.getPicId()!=0){
        PicturePo picturePo = pictureMapper.selectByPrimaryKey(customerPo.getPicId());
        customerInfoDto.setPictureInfo(picturePo);
      }

      res = BaseResult.success("查询成功");
      res.setData(customerInfoDto);

    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询失败");
    }

    return res;
  }

  @Override
  public BaseResult rechargeAmount(RechargeDto rechargeDto) {
    BaseResult res = null;

    try {

      int customerId = rechargeDto.getCustomerId();
      BigDecimal amount = rechargeDto.getAmount();
      CustomerPo customerPo = customerMapper.selectByPrimaryKey(customerId);
      BigDecimal total = customerPo.getBalance();
      total = total.add(amount);
      CustomerPo newPo = new CustomerPo();
      newPo.setId(customerId);
      newPo.setBalance(total);
      customerMapper.updateByPrimaryKeySelective(newPo);

      res = BaseResult.success("充值成功");
      res.setData(total);

    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("充值失败");
    }

    return res;
  }

  @Override
  public BaseResult purchaseVipCard(PurchaseVipCardDto purchaseVipCardDto) {
    BaseResult res = null;

    try {

      int customerId = purchaseVipCardDto.getCustomerId();
      int gymId = purchaseVipCardDto.getGymId();
      int type = purchaseVipCardDto.getType();
      CustomerPo customerPo = customerMapper.selectByPrimaryKey(customerId);
      GymVipCardPoKey gymVipCardPoKey = new GymVipCardPoKey();
      gymVipCardPoKey.setGymId(gymId);
      gymVipCardPoKey.setType(type);
      GymVipCardPo gymVipCardPo = gymVipCardMapper.selectByPrimaryKey(gymVipCardPoKey);

      if (customerPo.getBalance().compareTo(gymVipCardPo.getPrice())<0){
        res = BaseResult.fail("购买失败 余额不足");
        return res;
      }

      List<VipCardPo> vipCard = vipCardMapperInCommons.selectByCustomerId(customerId);
      for(VipCardPo po:vipCard){
        if (po.getGymId()==gymId ){
          Date start = po.getCreatedTime();
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(start);
          changeToZeroOfDay(calendar);
          Calendar now = Calendar.getInstance();
          now.setTime(new Date());
          changeToZeroOfDay(now);
          boolean available = false;
          int differenceDays = differentDays(calendar,now);
          if (po.getType()== VipCardType.MONTHLY){
            available = (differenceDays>30)?false:true;
            calendar.add(Calendar.DATE,30);
          }else if (po.getType()== VipCardType.SESSIONLY){
            available = (differenceDays>90)?false:true;
            calendar.add(Calendar.DATE,90);
          }else if (po.getType()== VipCardType.YEARLY){
            available = (differenceDays>365)?false:true;
            calendar.add(Calendar.DATE,365);
          }
          if (available){
            res = BaseResult.fail("购买失败 会员卡仍在有效期内");
            return res;
          }
        }
      }

      VipOrderPo vipOrderPo = new VipOrderPo();
      vipOrderPo.setGymId(gymId);
      vipOrderPo.setCreatedTime(new Date());
      vipOrderPo.setOrderPrice(gymVipCardPo.getPrice());
      vipOrderPo.setStatus(VipOrderStatus.PAID);
      vipOrderMapper.insert(vipOrderPo);

      BigDecimal balance = customerPo.getBalance();
      balance = balance.subtract(gymVipCardPo.getPrice());
      CustomerPo newPo = new CustomerPo();
      newPo.setId(customerId);
      newPo.setBalance(balance);
      customerMapper.updateByPrimaryKeySelective(newPo);

      VipCardPo vipCardPo = new VipCardPo();
      vipCardPo.setCreatedTime(new Date());
      vipCardPo.setCustomerId(customerId);
      vipCardPo.setGymId(gymId);
      vipCardPo.setPrice(gymVipCardPo.getPrice());
      vipCardPo.setType(gymVipCardPo.getType());
      vipCardMapperInCommons.insert(vipCardPo);

      GymPo gymPo = gymMapper.selectByPrimaryKey(gymId);
      ReceiveRecordPo receiveRecordPo = new ReceiveRecordPo();
      receiveRecordPo.setAmount(gymVipCardPo.getPrice());
      receiveRecordPo.setCreatedTime(new Date());
      receiveRecordPo.setFromId(customerId);
      receiveRecordPo.setToId(gymPo.getBusinessId());
      receiveRecordPo.setType(ReceiveRecordType.VIP_CARD_FEE);
      receiveRecordMapper.insert(receiveRecordPo);

      res = BaseResult.success("购买成功");

    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("购买失败");
    }

    return res;
  }

  @Override
  public BaseResult getNotifies(Integer customerId) {
    BaseResult res = null;

    try {


      CustomerPo customerPo = customerMapper.selectByPrimaryKey(customerId);
      List<NotifiesDto> notifiesFromCoach = notifiesInCustomerMapper.selectAllFromCoachToCustomer(customerId);
      List<NotifiesDto> notifiesFromBusiness = notifiesInCustomerMapper.selectAllFromBusinessToCustomer(customerId);

      List<NotifiesDto> all = notifiesFromBusiness;
      all.addAll(notifiesFromCoach);

      Collections.sort(all, new Comparator<NotifiesDto>() {

        @Override
        public int compare(NotifiesDto o1, NotifiesDto o2) {

          long t1 = o1.getTime().getTime();
          long t2 = o2.getTime().getTime();
          if (t1>t2){
            return -1;
          }else if(t1<t2){
            return 1;
          }else {
            return 0;
          }
        }
      });



      res = BaseResult.success("获取成功");
      res.setData(all);

    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("获取失败");
    }

    return res;
  }

  @Override
  public BaseResult checkedNotifies(NotifiesPoKey notifies) {
    BaseResult res = null;

    try {

      NotifiesPo notifiesPo = notifiesMapper.selectByPrimaryKey(notifies);
      if (notifiesPo==null || notifiesPo.getFromId()==0){
        res = BaseResult.fail("已读失败 没有此消息");
        return res;
      }
      NotifiesPo newN = new NotifiesPo();
      BeanUtils.copyProperties(notifies,newN);
      newN.setStatus(1);
      notifiesMapper.updateByPrimaryKeySelective(newN);

      res = BaseResult.success("已读成功");

    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("已读失败");
    }

    return res;
  }

  @Override
  public BaseResult login(LoginDto loginDto) {
    BaseResult res = null;

    try {

      int id = 0;

      List<CustomerPo> list = customerMapper.selectAll();
      for(CustomerPo p:list){
        if (p.getPhone()==null)
          break;
        if (p.getPassword()==null)
          break;
        String ph = p.getPhone();
        String pw = p.getPassword();
        if (ph.equals(loginDto.getPhone()) && pw.equals(loginDto.getPassword())){
          id = p.getId();
          break;
        }
      }

      if (id==0){
        res = BaseResult.fail("登录失败");
      }else {
        res = BaseResult.success("登录成功");
        res.setData(id);
      }


    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("登录失败");
    }

    return res;
  }

  @Override
  public BaseResult register(RegisterDto registerDto) {
    BaseResult res = null;

    try {
      CustomerPo customerPo = new CustomerPo();
      BeanUtils.copyProperties(registerDto,customerPo);
      customerPo.setCreatedTime(new Date());
      customerPo.setBalance(BigDecimal.valueOf(0));


      if (customerPo.getPhone()==null || customerPo.getPhone().equals("")
          || customerPo.getPassword()==null || customerPo.getPassword().equals("")
          || customerPo.getUsername()==null || customerPo.getUsername().equals("")){
        res = BaseResult.fail("注册失败 手机号、用户名和密码均不能为空 ");
        return res;
      }

      List<CustomerPo> list = customerMapper.selectAll();
      for(CustomerPo p:list){
        if (p.getPhone()==null)
          break;
        String ph = p.getPhone();
        if (ph.equals(registerDto.getPhone())){
          res = BaseResult.fail("注册失败 该手机号已被注册 ");
          return res;
        }
      }


      customerMapper.insertSelective(customerPo);

      res = BaseResult.success("注册成功");

    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("注册失败");
    }

    return res;
  }

  @Override
  public BaseResult getPicInfo(Integer picId) {
    BaseResult res = null;

    try {

      PicturePo picturePo = pictureMapper.selectByPrimaryKey(picId);

      res = BaseResult.success("获取成功");
      res.setData(picturePo);

    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("获取失败");
    }

    return res;
  }

  @Override
  public BaseResult getPicGroupInfo(Integer picGroupId) {
    BaseResult res = null;

    try {

      List<PicturePo> pictures = pictureMapper.selectByPicGroupId(picGroupId);

      res = BaseResult.success("获取成功");
      res.setData(pictures);

    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("获取失败");
    }

    return res;
  }

  @Override
  public BaseResult uploadPic(MultipartFile file, Integer customerId) {
    BaseResult res = null;

    try {
      //保存图片
      String extension = FileUtils.getExtension(file.getOriginalFilename());
      if (extension==null || extension.equals("") ){
        throw new Exception();
      }
      long times = System.currentTimeMillis();
      if(FileUtils.savePic(file.getInputStream(),times+extension)){
        PicturePo picturePo = new PicturePo();
        picturePo.setCreatedTime(new Date());
        picturePo.setFilePath("/root/pic/"+times+extension);
        picturePo.setPicLink("/pic/"+times+extension);
        pictureMapper.insertReturnId(picturePo);
        //此处的picturePo中id自动被设置为数据库生成的主键id了
        //下一步可以直接使用 例如 设置用户的头像图片id
        CustomerPo customerPo = new CustomerPo();
        customerPo.setId(customerId);
        customerPo.setPicId(picturePo.getId());
        customerMapper.updateByPrimaryKeySelective(customerPo);
      }else{
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        res = BaseResult.fail("图片存储失败");
        return res;
      }

      res = BaseResult.success("保存图片成功");
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("保存图片失败");
    }

    return res;
  }

  /**
   * 日历比较相差几天
   * 注：如果两个是日期不相等，后面参数必须大于前面参数，即：cal2 > cal1
   * @param cal1
   * @param cal2
   * @return
   */
  public static int differentDays(Calendar cal1 , Calendar cal2 )
  {
    int day1= cal1.get(Calendar.DAY_OF_YEAR);
    int day2 = cal2.get(Calendar.DAY_OF_YEAR);

    int year1 = cal1.get(Calendar.YEAR);
    int year2 = cal2.get(Calendar.YEAR);
    if(year1 != year2)   //不同年
    {
      int timeDistance = 0 ;
      for(int i = year1 ; i < year2 ; i ++)
      {
        if(i%4==0 && i%100!=0 || i%400==0)    //闰年
        {
          timeDistance += 366;
        }
        else    //不是闰年
        {
          timeDistance += 365;
        }
      }

      return timeDistance + (day2-day1) ;
    }
    else    //同一年
    {
      return day2-day1;
    }
  }

  public static void changeToZeroOfDay(Calendar calendar){
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
  }
}
