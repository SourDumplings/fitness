package com.zju.se.nohair.fitness.customer.controller;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.customer.dto.CommentCourseDto;
import com.zju.se.nohair.fitness.customer.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 用户controller
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/1 13:26
 */
@RestController
@RequestMapping("customer")
@Api
public class CustomerController {

  private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  private CustomerService customerService;

  @ApiOperation(value = "顾客评论团课", httpMethod = "POST")
  @RequestMapping(value = "comment/public_course", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> createCommentForPublicCourse(@RequestBody CommentCourseDto commentCourseDto) {
    BaseResult baseResult = customerService.createCommentForPublicCourse(commentCourseDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "顾客评论私教课", httpMethod = "POST")
  @RequestMapping(value = "comment/private_course", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> createCommentForPrivateCourse(@RequestBody CommentCourseDto commentCourseDto) {
    BaseResult baseResult = customerService.createCommentForPrivateCourse(commentCourseDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

}
