package com.zju.se.nohair.fitness.coach.controller;

import com.zju.se.nohair.fitness.coach.dto.CommentCourseDto;
import com.zju.se.nohair.fitness.coach.dto.ResponseToPublicCourseDto;
import com.zju.se.nohair.fitness.coach.service.PublicCourseService;
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
 * 教练端  团课controller
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-20
 */

@RestController
@RequestMapping("coach/public_course")
@Api
public class CoachPublicCourseController {
  private static Logger logger = LoggerFactory.getLogger(CoachPublicCourseController.class);

  private PublicCourseService publicCourseService;

  @Autowired
  public void setPublicCourseService(PublicCourseService publicCourseService) {
    this.publicCourseService = publicCourseService;
  }

  @ApiOperation(value = "4团课中教练评论商家", httpMethod = "POST")
  @RequestMapping(value = "comment", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> createCommentForPublicCourse(@RequestBody CommentCourseDto commentCourseDto) {
    BaseResult baseResult = publicCourseService.createCommentForPublicCourse(commentCourseDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "教练响应团课", httpMethod = "POST")
  @RequestMapping(value = "response", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> responseToPublicCourse(
      @RequestBody ResponseToPublicCourseDto responseToPublicCourseDto) {
    BaseResult baseResult = publicCourseService
        .responseToPublicCourse(responseToPublicCourseDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "教练修改团课的响应价格", httpMethod = "PUT")
  @RequestMapping(value = "response", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Object> changeResponsePrice(
      @RequestBody ResponseToPublicCourseDto responseToPublicCourseDto) {
    BaseResult baseResult = publicCourseService.changeResponsePrice(responseToPublicCourseDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看团课列表；"
      + "不传 coachId 表示查看待响应的团课列表，传了 coachId 代表查看该教练响应成功的团课列表；"
      + "如果传了 courseDate 则选出相应日期的课程，格式：2019/12/16；", httpMethod = "GET")
  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listPublicCourses(
      @RequestParam(name = "coachId", required = false) Integer coachId,
      @RequestParam(name = "courseDate", required = false) Date courseDate) {
    BaseResult baseResult = null;
    if (coachId == null) {
      baseResult = publicCourseService.listPublicCoursesForResponsing(courseDate);
    } else {
      baseResult = publicCourseService
          .listResponsedPublicCourses(coachId, courseDate);
    }
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


 @ApiOperation(value = "查看教练响应的团课列表（我响应的团课）；（status=0代表商家未接受，status=1代表商家已接受，status=2代表商家已拒绝）", httpMethod = "GET")
 @RequestMapping(value = "response/{coachId}", method = RequestMethod.GET)
 @ResponseBody
 public ResponseEntity<Object> listResponsesByCoachId(
     @PathVariable(name = "coachId") Integer coachId) {
   BaseResult baseResult = publicCourseService.listResponsesByCoachId(coachId);
   if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
     return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
   } else {
     return new ResponseEntity<>(baseResult.getMessage(),
         HttpStatus.valueOf(baseResult.getStatus()));
   }
 }

  @ApiOperation(value = "查看教练结课团课列表", httpMethod = "GET")
  @RequestMapping(value = "finished/{coachId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listFinishedPublicCourses(
      @RequestParam(name = "coachId") Integer coachId) {
    BaseResult baseResult = publicCourseService.listFinishedPublicCourses(coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看教练待上团课列表", httpMethod = "GET")
  @RequestMapping(value = "required/{coachId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listRequiredPublicCourses(
      @RequestParam(name = "coachId") Integer coachId) {
    BaseResult baseResult = publicCourseService.listRequiredPublicCourses(coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "团课结课", httpMethod = "PUT")
  @RequestMapping(value = "{courseId}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Object> finishPublicCourseByCourseId(
      @PathVariable("courseId") Integer courseId) {
    BaseResult baseResult = publicCourseService.finishPublicCourseByCourseId(courseId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
