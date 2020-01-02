package com.zju.se.nohair.fitness.coach.controller;

import com.zju.se.nohair.fitness.coach.dto.CreatePrivateCourseDto;
import com.zju.se.nohair.fitness.coach.service.PrivateCourseService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
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

  @ApiOperation(value = "查看发布的私教课程的响应列表；响应状态（0代表未接受，1代表已接受，2代表已拒绝）", httpMethod = "GET")
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

}
