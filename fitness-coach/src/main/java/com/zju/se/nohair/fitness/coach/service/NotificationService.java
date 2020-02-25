package com.zju.se.nohair.fitness.coach.service;

import com.zju.se.nohair.fitness.coach.dto.SendNotificationDto;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * 教练通知模块的 Service 接口.
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-02-21
 */
public interface NotificationService {

  BaseResult listNotificationReceivedByCoachId(Integer coachId);//查看别人发给自己的通知（包含2商家对教练，和3用户对教练）

  BaseResult listNotificationSentByCoachId(Integer coachId);//查看教练对商家，顾客（私教课）的通知

  BaseResult notifyByIdAndType(SendNotificationDto sendNotificationDto);//发送通知（包含教练对商家，和教练对私教课用户）

  BaseResult listBusinessByCourseId(Integer courseId);//通知模块---课程id查找商家id,status=1

  BaseResult listPrivateCustomerByCourseId(Integer courseId);//通知模块---课程id查找私教课顾客id

  BaseResult listPublicCustomerByCourseId(Integer courseId);//通知模块 课程id查找团课所有顾客id
}
