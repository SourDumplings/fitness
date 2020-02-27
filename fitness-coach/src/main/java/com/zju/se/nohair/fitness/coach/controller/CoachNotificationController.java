package com.zju.se.nohair.fitness.coach.controller;

import com.zju.se.nohair.fitness.coach.dto.SendNotificationDto;
import com.zju.se.nohair.fitness.coach.service.NotificationService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.po.NotifiesPo;
import com.zju.se.nohair.fitness.dao.po.NotifiesPoKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

  @ApiOperation(value = "发送通知（包含教练对商家，和教练对私教课用户）", httpMethod = "POST")
  @RequestMapping(value = "", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> notifyByIdAndType(@RequestBody @Valid SendNotificationDto sendNotificationDto) {
    BaseResult baseResult = notificationService.notifyByIdAndType(sendNotificationDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
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

  @ApiOperation(value = "通知模块 课程id查找商家id,status=1", httpMethod = "GET")
  @RequestMapping(value = "{courseId}/business", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listBusinessByCourseId(@PathVariable("courseId") Integer courseId) {
    BaseResult baseResult = notificationService.listBusinessByCourseId(courseId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "通知模块 查找所有商家id", httpMethod = "GET")
  @RequestMapping(value = "business", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listBusinessList() {
    BaseResult baseResult = notificationService.listBusinessList();
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "通知模块 课程id查找私教课顾客id", httpMethod = "GET")
  @RequestMapping(value = "{courseId}/private_customer", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listPrivateCustomerByCourseId(@PathVariable("courseId") Integer courseId) {
    BaseResult baseResult = notificationService.listPrivateCustomerByCourseId(courseId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "通知模块 课程id查找团课所有顾客id", httpMethod = "GET")
  @RequestMapping(value = "{courseId}/public_customer", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listPublicCustomerByCourseId(@PathVariable("courseId") Integer courseId) {
    BaseResult baseResult = notificationService.listPublicCustomerByCourseId(courseId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }


}
