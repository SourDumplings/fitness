package com.zju.se.nohair.fitness.coach.service.impl;

import com.zju.se.nohair.fitness.coach.dto.NotificationListItemDto;
import com.zju.se.nohair.fitness.coach.service.NotificationService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.commons.utils.DateUtils;
import com.zju.se.nohair.fitness.dao.mapper.NotifiesMapper;
import com.zju.se.nohair.fitness.dao.po.NotifiesPo;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Autowired
  public void setNotifiesMapper(NotifiesMapper notifiesMapper) {
    this.notifiesMapper = notifiesMapper;
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
}
