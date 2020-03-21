package com.zju.se.nohair.fitness.coach.service.impl;

import com.zju.se.nohair.fitness.coach.dto.BusinessDto;
import com.zju.se.nohair.fitness.coach.dto.NotificationBusinessDto;
import com.zju.se.nohair.fitness.coach.dto.NotificationCustomerDto;
import com.zju.se.nohair.fitness.coach.dto.NotificationDetailDto;
import com.zju.se.nohair.fitness.coach.dto.NotificationListItemDto;
import com.zju.se.nohair.fitness.coach.dto.ReadNotificationDetailDto;
import com.zju.se.nohair.fitness.coach.dto.SendNotificationDto;
import com.zju.se.nohair.fitness.coach.service.NotificationService;
import com.zju.se.nohair.fitness.commons.constant.NotificationStatus;
import com.zju.se.nohair.fitness.commons.constant.NotificationType;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.commons.utils.DateUtils;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.NotifiesMapper;
import com.zju.se.nohair.fitness.dao.mapper.ResponsesPrivateMapper;
import com.zju.se.nohair.fitness.dao.mapper.TakesPrivateMapper;
import com.zju.se.nohair.fitness.dao.mapper.TakesPublicMapper;
import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import com.zju.se.nohair.fitness.dao.po.NotifiesPo;
import com.zju.se.nohair.fitness.dao.po.NotifiesPoKey;
import com.zju.se.nohair.fitness.dao.po.ResponsesPrivatePo;
import com.zju.se.nohair.fitness.dao.po.TakesPrivatePo;
import com.zju.se.nohair.fitness.dao.po.TakesPublicPoKey;
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

/**
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-02-21
 */
@Transactional(readOnly = true)
@Service
public class NotificationServiceImpl implements NotificationService {
  private static Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

  private NotifiesMapper notifiesMapper;

  private ResponsesPrivateMapper responsesPrivateMapper;

  private TakesPrivateMapper takesPrivateMapper;

  private TakesPublicMapper takesPublicMapper;

  private BusinessMapper businessMapper;

  @Autowired
  public void setBusinessMapper(BusinessMapper businessMapper) {
    this.businessMapper = businessMapper;
  }

  @Autowired
  public void setTakesPublicMapper(TakesPublicMapper takesPublicMapper) {
    this.takesPublicMapper = takesPublicMapper;
  }

  @Autowired
  public void setNotifiesMapper(NotifiesMapper notifiesMapper) {
    this.notifiesMapper = notifiesMapper;
  }

  @Autowired
  public void setResponsesPrivateMapper(ResponsesPrivateMapper responsesPrivateMapper) {
    this.responsesPrivateMapper = responsesPrivateMapper;
  }

  @Autowired
  public void setTakesPrivateMapper(TakesPrivateMapper takesPrivateMapper) {
    this.takesPrivateMapper = takesPrivateMapper;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult readNotification(NotifiesPoKey notifiesPoKey) {
    //阅读通知
    BaseResult res = null;

    try {

      List<NotifiesPo> notifiesPos = notifiesMapper.selectByNotifiesPoKey(notifiesPoKey);

      for (NotifiesPo notifiesPo : notifiesPos) {
        ReadNotificationDetailDto readNotificationDetailDto = new ReadNotificationDetailDto();
        BeanUtils.copyProperties(notifiesPo, readNotificationDetailDto);
        notifiesMapper.updateByNotifiesPoKey(notifiesPo);
      }


      res = BaseResult.success("阅读通知成功");
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("阅读通知失败");
    }

    return res;
  }

  @Override
  public BaseResult listNotificationReceivedByCoachId(Integer coachId) {
    //查看别人发给自己的通知（包含2商家对教练，和3用户对教练）
    BaseResult res = null;

    try {
      List<NotificationListItemDto> listItemDtos = new ArrayList<>();
      final List<NotifiesPo> notifiesPos = notifiesMapper.selectAllByToCoachId(coachId);
      for (NotifiesPo notifiesPo : notifiesPos) {
        NotificationListItemDto notificationListItemDto = new NotificationListItemDto();
        BeanUtils.copyProperties(notifiesPo, notificationListItemDto);
        notificationListItemDto.setTime(DateUtils.date2String(notifiesPo.getTime()));
        listItemDtos.add(notificationListItemDto);
      }

      res = BaseResult.success("查询教练收到的通知列表成功");
      res.setData(listItemDtos);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询教练收到的通知列表失败");
    }

    return res;
  }

  @Override
  public BaseResult listNotificationSentByCoachId(Integer coachId) {
    //查看教练对商家，顾客（私教课）的通知
    BaseResult res = null;

    try {
      List<NotificationListItemDto> listItemDtos = new ArrayList<>();
      final List<NotifiesPo> notifiesPos = notifiesMapper.selectAllByFromCoachId(coachId);
      for (NotifiesPo notifiesPo : notifiesPos) {
        NotificationListItemDto notificationListItemDto = new NotificationListItemDto();
        BeanUtils.copyProperties(notifiesPo, notificationListItemDto);
        notificationListItemDto.setTime(DateUtils.date2String(notifiesPo.getTime()));
        listItemDtos.add(notificationListItemDto);
      }

      res = BaseResult.success("查询教练对商家，顾客（私教课）的通知列表成功");
      res.setData(listItemDtos);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询教练对商家，顾客（私教课）的通知列表失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult notifyByIdAndType(SendNotificationDto sendNotificationDto) {
    //发送通知（包含教练对商家，和教练对私教课用户）
    BaseResult res = null;

    try {
      if (!sendNotificationDto.getType().equals(NotificationType.COACH_TO_CUSTOMER) &&
          !sendNotificationDto.getType().equals(NotificationType.COACH_TO_BUSINESS)) {
        return BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "通知类型错误！");
      }

      NotifiesPo notifiesPo = new NotifiesPo();
      BeanUtils.copyProperties(sendNotificationDto, notifiesPo);
      notifiesPo.setStatus(NotificationStatus.UNREAD);
      notifiesPo.setTime(new Date());
      notifiesMapper.insert(notifiesPo);
      res = BaseResult.success("发送通知（包含教练对商家，和教练对私教课用户）成功");
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("发送通知（包含教练对商家，和教练对私教课用户）失败");
    }

    return res;
  }

  @Override
  public BaseResult listBusinessByCourseId(Integer courseId) {
    //通知模块---课程id查找商家id,status=1
    BaseResult res = null;

    try {
      final List<ResponsesPrivatePo> responsesPrivatePos = responsesPrivateMapper.selectBusinessByCourseId(courseId);
      List<NotificationBusinessDto> notificationBusinessDtoList = new ArrayList<>();
      for (ResponsesPrivatePo responsesPrivatePo : responsesPrivatePos) {
        NotificationBusinessDto notificationBusinessDto = new NotificationBusinessDto();
        BeanUtils.copyProperties(responsesPrivatePo, notificationBusinessDto);

        notificationBusinessDtoList.add(notificationBusinessDto);
      }
      res = BaseResult.success("查找商家id,status=1成功");
      res.setData(notificationBusinessDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查找商家id,status=1失败");
    }

    return res;
  }

  @Override
  public BaseResult listPrivateCustomerByCourseId(Integer courseId) {
    //通知模块---课程id查找私教课顾客id
    BaseResult res = null;

    try {
      final List<TakesPrivatePo> takesPrivatePos = takesPrivateMapper.selectPrivateCustomerByCourseId(courseId);
      List<NotificationCustomerDto> notificationCustomerDtoList = new ArrayList<>();
      for (TakesPrivatePo takesPrivatePo : takesPrivatePos) {
        NotificationCustomerDto notificationCustomerDto = new NotificationCustomerDto();
        BeanUtils.copyProperties(takesPrivatePo, notificationCustomerDto);

        notificationCustomerDtoList.add(notificationCustomerDto);
      }
      res = BaseResult.success("查找私教课顾客id成功");
      res.setData(notificationCustomerDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查找私教课顾客id失败");
    }

    return res;
  }

  @Override
  public BaseResult listPublicCustomerByCourseId(Integer courseId) {
    //通知模块 课程id查找团课所有顾客id
    BaseResult res = null;

    try {
      final List<TakesPublicPoKey> takesPublicPoKeys = takesPublicMapper.selectPublicCustomerByCourseId(courseId);
      List<NotificationCustomerDto> notificationCustomerDtoList = new ArrayList<>();
      for (TakesPublicPoKey takesPublicPoKey : takesPublicPoKeys) {
        NotificationCustomerDto notificationCustomerDto = new NotificationCustomerDto();
        BeanUtils.copyProperties(takesPublicPoKey, notificationCustomerDto);

        notificationCustomerDtoList.add(notificationCustomerDto);
      }
      res = BaseResult.success("查找团课所有顾客id成功");
      res.setData(notificationCustomerDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查找团课所有顾客id失败");
    }

    return res;
  }

  @Override
  public BaseResult listBusinessList() {
    //通知模块 查找所有商家id
    BaseResult res = null;

    try {
      final List<BusinessPo> businessPos = businessMapper.selectAll();
      List<BusinessDto> businessDtoList = new ArrayList<>();
      for (BusinessPo businessPo : businessPos) {
        BusinessDto businessDto = new BusinessDto();
        BeanUtils.copyProperties(businessPo, businessDto);

        businessDtoList.add(businessDto);
      }
      res = BaseResult.success("查找所有商家id成功");
      res.setData(businessDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查找所有商家id失败");
    }

    return res;
  }




}
