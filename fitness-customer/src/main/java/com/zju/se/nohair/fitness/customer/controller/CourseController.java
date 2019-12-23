package com.zju.se.nohair.fitness.customer.controller;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.customer.service.CoachInCustomerService;
import com.zju.se.nohair.fitness.customer.service.PublicCourseInCustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程 controller.
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/11 19:20
 */
@RestController
@RequestMapping("customer/course")
@Api
public class CourseController {

  private static Logger logger = LoggerFactory.getLogger(CourseController.class);

  @Autowired
  private PublicCourseInCustomerService publicCourseService;
  @Autowired
  private CoachInCustomerService coachService;


  @ApiOperation(value = "查询状态1，2，4的团课列表", httpMethod = "GET")
  @RequestMapping(value = "list/public_course", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getPublicCourseList() {
    BaseResult baseResult = publicCourseService.getPublicCourseList();
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查询开设团课的场馆name列表", httpMethod = "GET")
  @RequestMapping(value = "list/gym_name", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getGymNameList() {
    BaseResult baseResult = publicCourseService.getGymNameList();
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "客户查询团课详细信息", httpMethod = "GET")
  @RequestMapping(value = "details/public_course/{courseId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getPublicCourseDetail(@PathVariable("courseId") Integer courseId) {
    BaseResult baseResult = publicCourseService.getPublicCourseDetail(courseId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }


  @ApiOperation(value = "查询客户是否已经报名过该团课", httpMethod = "GET")
  @RequestMapping(value = "check/public_course/{courseId}/{customerId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> checkCustomerChosenPublic(@PathVariable("courseId") Integer courseId,@PathVariable("customerId") Integer customerId) {
    BaseResult baseResult = publicCourseService.checkCustomerChosenPublicOrNot(courseId,customerId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "客户报名团课", httpMethod = "GET")
  @RequestMapping(value = "order/public_course/{courseId}/{customerId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> orderPublicCourse(@PathVariable("courseId") Integer courseId,@PathVariable("customerId") Integer customerId) {
    BaseResult baseResult = publicCourseService.selectPublicCourse(courseId,customerId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "客户退出团课", httpMethod = "GET")
  @RequestMapping(value = "cancel/public_course/{courseId}/{customerId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> cancelPublicCourse(@PathVariable("courseId") Integer courseId,@PathVariable("customerId") Integer customerId) {
    BaseResult baseResult = publicCourseService.cancelPublicCourse(courseId,customerId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "客户查询私教课", httpMethod = "GET")
  @RequestMapping(value = "list/private_course", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getPrivateCourseList() {
    /*BaseResult baseResult = publicCourseService.getPrivateCourseList();
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }*/
    return  null;
  }

}
