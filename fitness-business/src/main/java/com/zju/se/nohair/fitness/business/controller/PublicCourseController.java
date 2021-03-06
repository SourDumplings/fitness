package com.zju.se.nohair.fitness.business.controller;

import com.zju.se.nohair.fitness.business.dto.CreatePublicCourseDto;
import com.zju.se.nohair.fitness.business.service.PublicCourseService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
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
 * 团课 controller.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/11 8:56
 */
@RestController
@RequestMapping("business/public_course")
@Api
public class PublicCourseController {

  private static Logger logger = LoggerFactory.getLogger(PublicCourseController.class);

  private PublicCourseService publicCourseService;

  @Autowired
  public void setPublicCourseService(PublicCourseService publicCourseService) {
    this.publicCourseService = publicCourseService;
  }

  @ApiOperation(value = "发布新团课", httpMethod = "POST")
  @RequestMapping(value = "", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> createPublicCourse(
      @RequestBody @Valid CreatePublicCourseDto createPublicCourseDto) {
    BaseResult baseResult = publicCourseService.createPublicCourse(createPublicCourseDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "删除发布的团课", httpMethod = "DELETE")
  @RequestMapping(value = "{courseId}", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity<Object> deletePublicCourseByCourseId(
      @PathVariable("courseId") Integer courseId) {
    BaseResult baseResult = publicCourseService.deletePublicCourseByCourseId(courseId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看发布的团课列表；"
      + "课程状态（0代表商家已发布，1代表教练已选定，2代表至少一个顾客选定并已付款，3代表课程结束未评价，4代表已满，5代表课程结束已评价）；"
      + "如果传入 courseDate，那么只会列出该日期的至少已经响应成功（status != 0）的团课，courseDate 格式：2019/12/16", httpMethod = "GET")
  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listPublicCourses(
      @RequestParam(name = "businessId") Integer businessId,
      @RequestParam(name = "courseDate", required = false) Date courseDate) {
    BaseResult baseResult = publicCourseService.listPublicCourses(businessId, courseDate);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看发布的团课详情；课程状态（0代表商家已发布，1代表教练已选定，2代表至少一个顾客选定并已付款，3代表课程结束未评价，4代表已满，5代表课程结束已评价）", httpMethod = "GET")
  @RequestMapping(value = "{courseId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getPublicCourseDetailByCourseId(
      @PathVariable("courseId") Integer courseId) {
    BaseResult baseResult = publicCourseService.getPublicCourseDetailByCourseId(courseId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看发布的课程的响应列表；响应状态（0代表未接受，1代表已接受，2代表已拒绝）", httpMethod = "GET")
  @RequestMapping(value = "{courseId}/response", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listPublicCourseResponsesByCourseId(
      @PathVariable("courseId") Integer courseId) {
    BaseResult baseResult = publicCourseService.listPublicCourseResponsesByCourseId(courseId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看购买该课程的顾客列表", httpMethod = "GET")
  @RequestMapping(value = "{courseId}/customer", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listCustomersByCourseId(
      @PathVariable("courseId") Integer courseId) {
    BaseResult baseResult = publicCourseService.listCustomersByCourseId(courseId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "接受对于团课的响应", httpMethod = "PUT")
  @RequestMapping(value = "response/accept/{courseId}/{coachId}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Object> acceptResponse(
      @PathVariable("courseId") Integer courseId, @PathVariable("coachId") Integer coachId) {
    BaseResult baseResult = publicCourseService.acceptResponse(courseId, coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "拒绝对于团课的响应", httpMethod = "PUT")
  @RequestMapping(value = "response/deny/{courseId}/{coachId}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Object> denyResponse(
      @PathVariable("courseId") Integer courseId, @PathVariable("coachId") Integer coachId) {
    BaseResult baseResult = publicCourseService.denyResponse(courseId, coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

}
