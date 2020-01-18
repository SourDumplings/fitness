package com.zju.se.nohair.fitness.business.service.impl;

import com.zju.se.nohair.fitness.business.dto.NotificationDetailDto;
import com.zju.se.nohair.fitness.business.dto.NotificationListItemDto;
import com.zju.se.nohair.fitness.business.dto.SendNotificationDto;
import com.zju.se.nohair.fitness.business.service.NotificationService;
import com.zju.se.nohair.fitness.commons.constant.NotificationStatus;
import com.zju.se.nohair.fitness.commons.constant.NotificationType;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.NotifiesMapper;
import com.zju.se.nohair.fitness.dao.po.NotifiesPo;
import com.zju.se.nohair.fitness.dao.po.NotifiesPoKey;
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
 * 商家通知模块的 Service 接口实现.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/18 21:54
 */
@Transactional(readOnly = true)
@Service
public class NotificationServiceImpl implements NotificationService {

  private static Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

  private NotifiesMapper notifiesMapper;

  @Autowired
  public void setNotifiesMapper(NotifiesMapper notifiesMapper) {
    this.notifiesMapper = notifiesMapper;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult notifyByIdAndType(SendNotificationDto sendNotificationDto) {
    BaseResult res = null;

    try {
      if (!sendNotificationDto.getType().equals(NotificationType.BUSINESS_TO_COACH) &&
          !sendNotificationDto.getType().equals(NotificationType.BUSINESS_TO_CUSTOMER)) {
        return BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "通知类型错误！");
      }

      NotifiesPo notifiesPo = new NotifiesPo();
      BeanUtils.copyProperties(sendNotificationDto, notifiesPo);
      notifiesPo.setStatus(NotificationStatus.UNREAD);
      notifiesPo.setTime(new Date());
      notifiesMapper.insert(notifiesPo);
      res = BaseResult.success("发送通知成功");
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("发送通知失败");
    }

    return res;
  }

  @Override
  public BaseResult listNotificationSentByBusinessId(Integer businessId) {
    BaseResult res = null;

    try {
      List<NotificationListItemDto> listItemDtos = new ArrayList<>();
      final List<NotifiesPo> notifiesPos = notifiesMapper.selectAllByFromBusinessId(businessId);
      for (NotifiesPo notifiesPo : notifiesPos) {
        NotificationListItemDto notificationListItemDto = new NotificationListItemDto();
        BeanUtils.copyProperties(notifiesPo, notificationListItemDto);
        listItemDtos.add(notificationListItemDto);
      }

      res = BaseResult.success("查询商家发送的通知列表成功");
      res.setData(listItemDtos);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询商家发送的通知列表失败");
    }

    return res;
  }

  @Override
  public BaseResult listNotificationReceivedByBusinessId(Integer businessId) {
    BaseResult res = null;

    try {
      List<NotificationListItemDto> listItemDtos = new ArrayList<>();
      final List<NotifiesPo> notifiesPos = notifiesMapper.selectAllByToBusinessId(businessId);
      for (NotifiesPo notifiesPo : notifiesPos) {
        NotificationListItemDto notificationListItemDto = new NotificationListItemDto();
        BeanUtils.copyProperties(notifiesPo, notificationListItemDto);
        listItemDtos.add(notificationListItemDto);
      }

      res = BaseResult.success("查询商家收到的通知列表成功");
      res.setData(listItemDtos);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询商家收到的通知列表失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult readNotification(NotifiesPoKey notifiesPoKey) {
    BaseResult res = null;

    try {
      NotifiesPo notifiesPo = notifiesMapper.selectByPrimaryKey(notifiesPoKey);
      NotificationDetailDto notificationDetailDto = new NotificationDetailDto();
      BeanUtils.copyProperties(notifiesPo, notificationDetailDto);
      notifiesPo.setStatus(NotificationStatus.READ);
      notifiesMapper.updateByPrimaryKey(notifiesPo);
      res = BaseResult.success("阅读通知成功");
      res.setData(notificationDetailDto);
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("阅读通知失败");
    }

    return res;
  }
}