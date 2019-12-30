package com.zju.se.nohair.fitness.business.controller;

import com.zju.se.nohair.fitness.business.dto.ResponseToPrivateCourseDto;
import com.zju.se.nohair.fitness.business.service.PrivateCourseService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
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
 * 私教课 controller.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/17 15:37
 */
@RestController
@RequestMapping("business/private_course")
@Api
public class PrivateCourseController {

  private static Logger logger = LoggerFactory.getLogger(PrivateCourseController.class);

  private PrivateCourseService privateCourseService;

  @Autowired
  public void setPrivatteCourseService(PrivateCourseService privateCourseService) {
    this.privateCourseService = privateCourseService;
  }

  @ApiOperation(value = "响应私教课", httpMethod = "POST")
  @RequestMapping(value = "response", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> responseToPrivateCourse(
      @RequestBody ResponseToPrivateCourseDto responseToPrivateCourseDto) {
    BaseResult baseResult = privateCourseService
        .responseToPrivateCourse(responseToPrivateCourseDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "更改对于私教课的响应价格", httpMethod = "PUT")
  @RequestMapping(value = "response", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Object> changeResponsePrice(
      @RequestBody ResponseToPrivateCourseDto responseToPrivateCourseDto) {
    BaseResult baseResult = privateCourseService.changeResponsePrice(responseToPrivateCourseDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "删除对于私教课的响应", httpMethod = "DELETE")
  @RequestMapping(value = "response", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity<Object> deleteResponse(@RequestParam(name = "courseId") Integer courseId,
      @RequestParam(name = "businessId") Integer businessId) {
    BaseResult baseResult = privateCourseService.deleteResponse(courseId, businessId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查询商家对于私教课的响应列表；响应状态（0代表未接受，1代表已接受，2代表已拒绝）", httpMethod = "GET")
  @RequestMapping(value = "response/{businessId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listResponsesByBusinessId(
      @PathVariable(name = "businessId") Integer businessId) {
    BaseResult baseResult = privateCourseService.listResponsesByBusinessId(businessId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看私教课列表；"
      + "不传 businessId 表示查看待响应的，传了代表查看该商家响应成功的；"
      + "如果传了 courseDate 那么只会选出相应日期的课程，格式：2019/12/16；", httpMethod = "GET")
  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listPrivateCourses(
      @RequestParam(name = "businessId", required = false) Integer businessId,
      @RequestParam(name = "courseDate", required = false) Date courseDate) {
    BaseResult baseResult = null;
    if (businessId == null) {
      baseResult = privateCourseService.listPrivateCoursesForResponsing(courseDate);
    } else {
      baseResult = privateCourseService
          .listResponsedPrivateCourses(businessId, courseDate);
    }
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查询私教课程详情；课程状态（0代表教练已发布，1代表商家已选定，2代表顾客选定并已付款，3代表课程结束未评价，4代表课程结束已评价）", httpMethod = "GET")
  @RequestMapping(value = "{courseId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getPrivateCourseDetailByCourseId(
      @PathVariable(name = "courseId") Integer courseId) {
    BaseResult baseResult = privateCourseService
        .getPrivateCourseDetailByCourseId(courseId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }


}
