package com.zju.se.nohair.fitness.customer.service.impl;

import com.zju.se.nohair.fitness.commons.constant.PrivateCourseStatus;
import com.zju.se.nohair.fitness.commons.constant.PublicCourseStatus;
import com.zju.se.nohair.fitness.commons.constant.VipCardType;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.customer.dao.mapper.VipCardInCustomerMapper;
import com.zju.se.nohair.fitness.customer.dto.CommentCourseDto;
import com.zju.se.nohair.fitness.customer.dto.VipCardWithGymInfoDto;
import com.zju.se.nohair.fitness.customer.service.CustomerService;
import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.mapper.PrivateCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.PublicCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.RatesMapper;
import com.zju.se.nohair.fitness.dao.mapper.VipCardMapper;
import com.zju.se.nohair.fitness.dao.po.CustomerPo;
import com.zju.se.nohair.fitness.dao.po.PrivateCoursePo;
import com.zju.se.nohair.fitness.dao.po.PublicCoursePo;
import com.zju.se.nohair.fitness.dao.po.RatesPo;
import com.zju.se.nohair.fitness.dao.po.TakesPublicPoKey;
import com.zju.se.nohair.fitness.dao.po.VipCardPo;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
