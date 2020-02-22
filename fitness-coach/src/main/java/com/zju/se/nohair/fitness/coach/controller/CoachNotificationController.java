package com.zju.se.nohair.fitness.coach.controller;

import com.zju.se.nohair.fitness.coach.service.NotificationService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 教练通知模块的Controller
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-02-21
 */
@RestController
@RequestMapping("coach/notification")
@Api
public class CoachNotificationController {
  private static Logger logger = LoggerFactory.getLogger(CoachNotificationController.class);

  private NotificationService notificationService;

  @Autowired
  public void setNotificationService(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @ApiOperation(value = "查看教练对商家的通知，顾客（私教课）的通知；"
      + "通知类型（0代表教练对私教课顾客，4代表教练对商家）；"
      + "通知状态（0未读，1代表已读）", httpMethod = "GET")
  @RequestMapping(value = "sent", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listNotificationSentByCoachId(
      @RequestParam(name = "coachId") Integer coachId) {
    BaseResult baseResult = notificationService.listNotificationSentByCoachId(coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看别人发给自己的通知（包含2商家对教练，和3用户对教练）；"
      + "通知类型（2代表商家对教练，3代表用户对教练）；"
      + "通知状态（0未读，1代表已读）", httpMethod = "GET")
  @RequestMapping(value = "received", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listNotificationReceivedByCoachId(@RequestParam(name = "coachId") Integer coachId) {
    BaseResult baseResult = notificationService.listNotificationReceivedByCoachId(coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
