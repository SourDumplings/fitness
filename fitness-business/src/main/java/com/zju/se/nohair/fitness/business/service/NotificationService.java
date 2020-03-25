package com.zju.se.nohair.fitness.business.service;

import com.zju.se.nohair.fitness.business.dto.NotifyPublicCourseMembersDto;
import com.zju.se.nohair.fitness.business.dto.ReadNotificationDto;
import com.zju.se.nohair.fitness.business.dto.SendNotificationDto;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * 商家通知模块的 Service 接口.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/18 21:45
 */
public interface NotificationService {

  BaseResult notifyByIdAndType(SendNotificationDto sendNotificationDto);

  BaseResult notifyPublicCourseMembers(NotifyPublicCourseMembersDto notifyPublicCourseMembersDto);

  BaseResult listNotificationSentByBusinessId(Integer businessId);

  BaseResult listNotificationReceivedByBusinessId(Integer businessId);

  BaseResult readNotification(ReadNotificationDto readNotificationDto);
}
