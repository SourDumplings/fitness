package com.zju.se.nohair.fitness.business.controller;

import com.zju.se.nohair.fitness.business.dto.CreatePublicCourseDto;
import com.zju.se.nohair.fitness.business.service.PublicCourseService;
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
}
