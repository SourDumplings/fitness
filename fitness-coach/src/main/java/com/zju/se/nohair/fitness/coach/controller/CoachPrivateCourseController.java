package com.zju.se.nohair.fitness.coach.controller;

import com.zju.se.nohair.fitness.coach.dto.CreatePrivateCourseDto;
import com.zju.se.nohair.fitness.coach.service.PrivateCourseService;
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
 * 教练端 私教课controller
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-20
 */
@RestController
@RequestMapping("coach/private_course")
@Api
public class CoachPrivateCourseController {
  private static Logger logger = LoggerFactory.getLogger(CoachPrivateCourseController.class);

  private PrivateCourseService privateCourseService;

  @Autowired
  public void setPrivatteCourseService(PrivateCourseService privateCourseService) {
    this.privateCourseService = privateCourseService;
  }

  @ApiOperation(value = "查看响应私教课的商家列表", httpMethod = "GET")
  @RequestMapping(value = "{courseId}/response", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listPrivateCourseResponsesByCourseId(
      @PathVariable("courseId") Integer courseId) {
    BaseResult baseResult = privateCourseService.listPrivateCourseResponsesByCourseId(courseId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "教练发布私教课", httpMethod = "POST")
  @RequestMapping(value = "", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> createPrivateCourse(
      @RequestBody @Valid CreatePrivateCourseDto createPrivateCourseDto) {
    BaseResult baseResult = privateCourseService.createPrivateCourse(createPrivateCourseDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "删除发布的私教课", httpMethod = "DELETE")
  @RequestMapping(value = "{courseId}", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity<Object> deletePrivateCourseByCourseId(
      @PathVariable("courseId") Integer courseId) {
    BaseResult baseResult = privateCourseService.deletePrivateCourseByCourseId(courseId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "接受商家对于私教课的响应", httpMethod = "PUT")
  @RequestMapping(value = "response/accept/{courseId}/{businessId}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Object> acceptResponse(
      @PathVariable("courseId") Integer courseId, @PathVariable("businessId") Integer businessId) {
    BaseResult baseResult = privateCourseService.acceptResponse(courseId, businessId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看教练发布的私教课列表；"
      + "课程状态（0代表教练已发布，1代表商家已选定，2代表顾客选定并已付款，3代表课程结束未评价，4代表课程结束已评价）；"
      + "coachId 为必填项，courseDate可填可不填；如果传入 courseDate，那么只会列出该日期中status != 0的私教课，courseDate 格式：2019/12/16", httpMethod = "GET")
  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listPrivateCourses(
      @RequestParam(name = "coachId") Integer coachId,
      @RequestParam(name = "courseDate", required = false) Date courseDate) {
    BaseResult baseResult = privateCourseService.listPrivateCourses(coachId, courseDate);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看发布的私教课详情；课程状态（0代表教练已发布，1代表商家已选定，2代表顾客选定并已付款，3代表课程结束未评价，4代表课程结束已评价）", httpMethod = "GET")
  @RequestMapping(value = "{courseId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getPrivateCourseDetailByCourseId(
      @PathVariable("courseId") Integer courseId) {
    BaseResult baseResult = privateCourseService.getPrivateCourseDetailByCourseId(courseId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看待响应的私教课详情", httpMethod = "GET")
  @RequestMapping(value = "details/{coachId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getPrivateCourseDetailByCoachId(
      @PathVariable("coachId") Integer coachId) {
    BaseResult baseResult = privateCourseService.getPrivateCourseDetailByCoachId(coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看教练结课私教课列表", httpMethod = "GET")
  @RequestMapping(value = "finished/{coachId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listFinishedPrivateCourses(
      @RequestParam(name = "coachId") Integer coachId) {
    BaseResult baseResult = privateCourseService.listFinishedPrivateCourses(coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看教练待上私教课列表", httpMethod = "GET")
  @RequestMapping(value = "required/{coachId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listRequiredPrivateCourses(
      @RequestParam(name = "coachId") Integer coachId) {
    BaseResult baseResult = privateCourseService.listRequiredPrivateCourses(coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "私教课结课", httpMethod = "PUT")
  @RequestMapping(value = "{courseId}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Object> finishPrivateCourseByCourseId(
      @PathVariable("courseId") Integer courseId) {
    BaseResult baseResult = privateCourseService.finishPrivateCourseByCourseId(courseId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
