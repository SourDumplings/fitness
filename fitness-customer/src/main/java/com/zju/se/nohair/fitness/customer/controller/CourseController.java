package com.zju.se.nohair.fitness.customer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

  @ApiOperation(value = "hello world 测试（测试用）", httpMethod = "GET")
  @RequestMapping(value = "hello", method = RequestMethod.GET)
  public String sayHi() {
    logger.warn("say hihihihihi..................");
    return "hello";
  }
}
