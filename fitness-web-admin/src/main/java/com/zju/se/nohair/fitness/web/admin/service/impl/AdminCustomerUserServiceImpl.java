package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.commons.constant.GenderTag;
import com.zju.se.nohair.fitness.commons.constant.VipCardType;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.commons.utils.DateUtils;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.mapper.GymMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.mapper.PrivateCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.PublicCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.ReceiveRecordMapper;
import com.zju.se.nohair.fitness.dao.mapper.TakesPrivateMapper;
import com.zju.se.nohair.fitness.dao.mapper.TakesPublicMapper;
import com.zju.se.nohair.fitness.dao.mapper.TimeSlotMapper;
import com.zju.se.nohair.fitness.dao.mapper.VipCardMapper;
import com.zju.se.nohair.fitness.dao.po.CustomerPo;
import com.zju.se.nohair.fitness.dao.po.PicturePo;
import com.zju.se.nohair.fitness.dao.po.PrivateCoursePo;
import com.zju.se.nohair.fitness.dao.po.PublicCoursePo;
import com.zju.se.nohair.fitness.dao.po.ReceiveRecordPo;
import com.zju.se.nohair.fitness.dao.po.TakesPrivatePo;
import com.zju.se.nohair.fitness.dao.po.TakesPublicPoKey;
import com.zju.se.nohair.fitness.dao.po.TimeSlotPo;
import com.zju.se.nohair.fitness.dao.po.VipCardPo;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCreateCustomerUserDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCustomerUserCourseBoughtListItemDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCustomerUserDetailDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCustomerUserListItemDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCustomerUserVipCardListItemDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminCustomerUserService;
import com.zju.se.nohair.fitness.web.admin.utils.PicUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 后台模块的用户子模块下的顾客用户 Service 接口实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/19 16:06
 */
@Transactional(readOnly = true)
@Service
public class AdminCustomerUserServiceImpl implements AdminCustomerUserService {

  private static Logger logger = LoggerFactory.getLogger(AdminCustomerUserServiceImpl.class);

  private CustomerMapper customerMapper;

  private PictureMapper pictureMapper;

  private ReceiveRecordMapper receiveRecordMapper;

  private VipCardMapper vipCardMapper;

  private GymMapper gymMapper;

  private BusinessMapper businessMapper;

  private TakesPrivateMapper takesPrivateMapper;

  private TakesPublicMapper takesPublicMapper;

  private PublicCourseMapper publicCourseMapper;

  private PrivateCourseMapper privateCourseMapper;

  private TimeSlotMapper timeSlotMapper;

  private CoachMapper coachMapper;

  @Autowired
  public void setCustomerMapper(CustomerMapper customerMapper) {
    this.customerMapper = customerMapper;
  }

  @Autowired
  public void setPictureMapper(PictureMapper pictureMapper) {
    this.pictureMapper = pictureMapper;
  }

  @Autowired
  public void setReceiveRecordMapper(ReceiveRecordMapper receiveRecordMapper) {
    this.receiveRecordMapper = receiveRecordMapper;
  }

  @Autowired
  public void setVipCardMapper(VipCardMapper vipCardMapper) {
    this.vipCardMapper = vipCardMapper;
  }

  @Autowired
  public void setGymMapper(GymMapper gymMapper) {
    this.gymMapper = gymMapper;
  }

  @Autowired
  public void setBusinessMapper(BusinessMapper businessMapper) {
    this.businessMapper = businessMapper;
  }

  @Autowired
  public void setTakesPublicMapper(TakesPublicMapper takesPublicMapper) {
    this.takesPublicMapper = takesPublicMapper;
  }

  @Autowired
  public void setTakesPrivateMapper(TakesPrivateMapper takesPrivateMapper) {
    this.takesPrivateMapper = takesPrivateMapper;
  }

  @Autowired
  public void setPrivateCourseMapper(PrivateCourseMapper privateCourseMapper) {
    this.privateCourseMapper = privateCourseMapper;
  }

  @Autowired
  public void setPublicCourseMapper(PublicCourseMapper publicCourseMapper) {
    this.publicCourseMapper = publicCourseMapper;
  }

  @Autowired
  public void setTimeSlotMapper(TimeSlotMapper timeSlotMapper) {
    this.timeSlotMapper = timeSlotMapper;
  }

  @Autowired
  public void setCoachMapper(CoachMapper coachMapper) {
    this.coachMapper = coachMapper;
  }

  @Override
  public BaseResult listAll() {
    BaseResult res = null;

    try {
      final List<CustomerPo> customerPoList = customerMapper
          .selectAll();
      List<AdminCustomerUserListItemDto> adminCustomerUserListItemDtoList = new ArrayList<>();
      for (CustomerPo customerPo : customerPoList) {
        AdminCustomerUserListItemDto adminCustomerUserListItemDto = new AdminCustomerUserListItemDto();
        BeanUtils.copyProperties(customerPo, adminCustomerUserListItemDto);

        if (GenderTag.MALE.equals(customerPo.getGender())) {
          adminCustomerUserListItemDto.setGender("男");
        } else {
          adminCustomerUserListItemDto.setGender("女");
        }

        adminCustomerUserListItemDto.setAge(DateUtils.getAgeFromBirthday(customerPo.getBirthday()));

        adminCustomerUserListItemDtoList.add(adminCustomerUserListItemDto);
      }
      res = BaseResult.success("查询顾客列表成功");
      res.setData(adminCustomerUserListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询顾客列表失败");
    }

    return res;
  }

  @Override
  public BaseResult getDetailById(Integer id) {
    BaseResult res = null;

    try {
      final CustomerPo customerPo = customerMapper.selectByPrimaryKey(id);
      if (customerPo == null) {
        return BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "没有此顾客");
      }

      AdminCustomerUserDetailDto adminCustomerUserDetailDto = new AdminCustomerUserDetailDto();

      BeanUtils.copyProperties(customerPo, adminCustomerUserDetailDto);

      final PicturePo picturePo = pictureMapper.selectByPrimaryKey(customerPo.getPicId());
      adminCustomerUserDetailDto.setPicLink(picturePo.getPicLink());

      // 月支出、总支出
      Date nowDate = new Date();
      Date oneMonthBack = DateUtils.dateBack(nowDate, 0, 1, 0);
      BigDecimal monthlyExpenses = BigDecimal.ZERO;
      BigDecimal totalExpenses = BigDecimal.ZERO;
      final List<ReceiveRecordPo> receiveRecordPos = receiveRecordMapper
          .selectAllCustomerExpensesByCustomerId(id);
      for (ReceiveRecordPo receiveRecordPo : receiveRecordPos) {
        final BigDecimal amount = receiveRecordPo.getAmount();
        if (oneMonthBack.compareTo(receiveRecordPo.getCreatedTime()) <= 0) {
          monthlyExpenses = monthlyExpenses.add(amount);
        }
        totalExpenses = totalExpenses.add(amount);
      }
      adminCustomerUserDetailDto.setMonthlyExpense(monthlyExpenses);
      adminCustomerUserDetailDto.setTotalExpense(totalExpenses);

      // 会员卡信息
      List<AdminCustomerUserVipCardListItemDto> vipCardListItemDtos = new ArrayList<>();
      final List<VipCardPo> vipCardPos = vipCardMapper.selectByCustomerId(id);
      for (VipCardPo vipCardPo : vipCardPos) {
        AdminCustomerUserVipCardListItemDto adminCustomerUserVipCardListItemDto = new AdminCustomerUserVipCardListItemDto();
        BeanUtils.copyProperties(vipCardPo, adminCustomerUserVipCardListItemDto);

        adminCustomerUserVipCardListItemDto
            .setBusinessName(businessMapper.selectByPrimaryKey(
                gymMapper.selectByPrimaryKey(vipCardPo.getGymId()).getBusinessId()).getUsername());

        final Date fromTime = vipCardPo.getCreatedTime();
        final Integer type = vipCardPo.getType();
        Date toTime = null;
        if (VipCardType.YEARLY.equals(type)) {
          toTime = DateUtils.dateFuture(fromTime, 1, 0, 0);
        } else if (VipCardType.SESSIONLY.equals(type)) {
          toTime = DateUtils.dateFuture(fromTime, 0, 1, 0);
        } else if (VipCardType.MONTHLY.equals(type)) {
          toTime = DateUtils.dateFuture(fromTime, 0, 3, 0);
        }
        adminCustomerUserVipCardListItemDto.setValidPeriod(DateUtils.datePart(fromTime) + "~" +
            DateUtils.datePart(toTime));

        vipCardListItemDtos.add(adminCustomerUserVipCardListItemDto);
      }
      adminCustomerUserDetailDto.setVipCardList(vipCardListItemDtos);

      // 已购课程
      List<AdminCustomerUserCourseBoughtListItemDto> courseBoughtListItemDtos = new ArrayList<>();
      final List<TakesPublicPoKey> takesPublicPoKeys = takesPublicMapper.selectByCustomerId(id);
      for (TakesPublicPoKey takesPublicPoKey : takesPublicPoKeys) {
        AdminCustomerUserCourseBoughtListItemDto adminCustomerUserCourseBoughtListItemDto = new AdminCustomerUserCourseBoughtListItemDto();
        final Integer courseId = takesPublicPoKey.getCourseId();
        final PublicCoursePo publicCoursePo = publicCourseMapper.selectByPrimaryKey(courseId);
        adminCustomerUserCourseBoughtListItemDto.setId(publicCoursePo.getId());
        adminCustomerUserCourseBoughtListItemDto.setType("团课");
        adminCustomerUserCourseBoughtListItemDto.setBusinessUsername(
            businessMapper.selectByPrimaryKey(publicCoursePo.getBusinessId()).getUsername());
        adminCustomerUserCourseBoughtListItemDto
            .setCoachName(coachMapper.selectByPrimaryKey(publicCoursePo.getCoachId()).getName());

        final TimeSlotPo timeSlotPo = timeSlotMapper
            .selectByPrimaryKey(publicCoursePo.getTimeSlotId());
        adminCustomerUserCourseBoughtListItemDto.setCourseTime(DateUtils
            .courseTime(publicCoursePo.getCourseDate(), timeSlotPo.getBeginTime(),
                timeSlotPo.getEndTime()));

        adminCustomerUserCourseBoughtListItemDto.setPrice(publicCoursePo.getPrice());
        courseBoughtListItemDtos.add(adminCustomerUserCourseBoughtListItemDto);
      }

      final List<TakesPrivatePo> takesPrivatePos = takesPrivateMapper.selectByCustomerId(id);
      for (TakesPrivatePo takesPrivatePo : takesPrivatePos) {
        AdminCustomerUserCourseBoughtListItemDto adminCustomerUserCourseBoughtListItemDto = new AdminCustomerUserCourseBoughtListItemDto();
        final Integer courseId = takesPrivatePo.getCourseId();
        final PrivateCoursePo privateCoursePo = privateCourseMapper.selectByPrimaryKey(courseId);
        adminCustomerUserCourseBoughtListItemDto.setId(privateCoursePo.getId());
        adminCustomerUserCourseBoughtListItemDto.setType("私教课");
        adminCustomerUserCourseBoughtListItemDto.setBusinessUsername(
            businessMapper.selectByPrimaryKey(privateCoursePo.getBusinessId()).getUsername());
        adminCustomerUserCourseBoughtListItemDto
            .setCoachName(coachMapper.selectByPrimaryKey(privateCoursePo.getCoachId()).getName());

        final TimeSlotPo timeSlotPo = timeSlotMapper
            .selectByPrimaryKey(takesPrivatePo.getTimeSlotId());
        adminCustomerUserCourseBoughtListItemDto.setCourseTime(DateUtils
            .courseTime(privateCoursePo.getCourseDate(), timeSlotPo.getBeginTime(),
                timeSlotPo.getEndTime()));

        adminCustomerUserCourseBoughtListItemDto.setPrice(privateCoursePo.getPrice());
        courseBoughtListItemDtos.add(adminCustomerUserCourseBoughtListItemDto);
      }
      adminCustomerUserDetailDto.setCourseList(courseBoughtListItemDtos);

      res = BaseResult.success("查询顾客详情成功");
      res.setData(adminCustomerUserDetailDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询顾客详情失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult saveItem(AdminCreateCustomerUserDto adminCreateCustomerUserDto,
      MultipartFile profilePic) {
    BaseResult res = null;

    try {
      boolean isCreating = false;
      if (adminCreateCustomerUserDto.getId() == -1) {
        isCreating = true;
        adminCreateCustomerUserDto.setId(null);
      }

      CustomerPo customerPo = new CustomerPo();
      BeanUtils.copyProperties(adminCreateCustomerUserDto, customerPo);
      customerPo.setPassword(DigestUtils.md5DigestAsHex(customerPo.getPassword().getBytes()));
      customerPo.setBalance(BigDecimal.ZERO);
      customerPo.setPicId(PicUtils.saveSinglePic(pictureMapper, profilePic));

      if (isCreating) {
        customerPo.setCreatedTime(new Date());
        customerMapper.insert(customerPo);
        res = BaseResult.success("顾客用户注册成功");
      } else {
        final CustomerPo target = customerMapper
            .selectByPrimaryKey(adminCreateCustomerUserDto.getId());
        if (target == null) {
          res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "无此 id 的顾客用户");
        } else {
          customerPo.setCreatedTime(target.getCreatedTime());
          customerMapper.updateByPrimaryKey(customerPo);
          res = BaseResult.success("顾客信息更新成功");
        }
      }

    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("顾客用户注册/更新失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult deleteItem(Integer id) {
    BaseResult res = null;

    try {
      customerMapper.deleteByPrimaryKey(id);
      res = BaseResult.success("删除顾客用户成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("删除顾客用户失败");
    }

    return res;
  }
}
