package com.zju.se.nohair.fitness.business.controller;

import com.zju.se.nohair.fitness.business.dto.NotifyPublicCourseMembersDto;
import com.zju.se.nohair.fitness.business.dto.ReadNotificationDto;
import com.zju.se.nohair.fitness.business.dto.SendNotificationDto;
import com.zju.se.nohair.fitness.business.service.NotificationService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商家通知相关 Controller.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/18 22:18
 */
@RestController
@RequestMapping("business/notification")
@Api
public class NotificationController {

  private static Logger logger = LoggerFactory.getLogger(NotificationController.class);

  private NotificationService notificationService;

  @Autowired
  public void setNotificationService(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @ApiOperation(value = "发送通知", httpMethod = "POST")
  @RequestMapping(value = "", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> notifyByIdAndType(
      @RequestBody @Valid SendNotificationDto sendNotificationDto) {
    BaseResult baseResult = notificationService.notifyByIdAndType(sendNotificationDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "群发团课通知；通知类型（1代表商家对顾客，2代表商家对教练）", httpMethod = "POST")
  @RequestMapping(value = "course", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> notifyPublicCourseMembers(
      @RequestBody @Valid NotifyPublicCourseMembersDto notifyPublicCourseMembersDto) {
    BaseResult baseResult = notificationService
        .notifyPublicCourseMembers(notifyPublicCourseMembersDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看发送的通知列表；"
      + "通知类型（1代表商家对顾客，2代表商家对教练）；"
      + "通知状态（0未读，1代表已读）", httpMethod = "GET")
  @RequestMapping(value = "sent", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listNotificationSentByBusinessId(
      @RequestParam(name = "businessId") Integer businessId) {
    BaseResult baseResult = notificationService.listNotificationSentByBusinessId(businessId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看收到的通知列表；"
      + "通知类型（4代表教练对商家，5代表顾客对商家）；"
      + "通知状态（0未读，1代表已读）", httpMethod = "GET")
  @RequestMapping(value = "received", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listNotificationReceivedByBusinessId(
      @RequestParam(name = "businessId") Integer businessId) {
    BaseResult baseResult = notificationService.listNotificationReceivedByBusinessId(businessId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "阅读通知；传入时间格式：yyyy/MM/dd HH:mm:ss"
      + "通知类型（4代表教练对商家，5代表顾客对商家）", httpMethod = "PUT")
  @RequestMapping(value = "read", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Object> readNotification(
      @RequestBody ReadNotificationDto readNotificationDto) {
    BaseResult baseResult = notificationService.readNotification(readNotificationDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "删除通知；传入时间格式：yyyy/MM/dd HH:mm:ss"
      + "通知类型（4代表教练对商家，5代表顾客对商家）", httpMethod = "DELETE")
  @RequestMapping(value = "", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity<Object> deleteNotification(
      @RequestBody ReadNotificationDto readNotificationDto) {
    BaseResult baseResult = notificationService.deleteNotification(readNotificationDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
