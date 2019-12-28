package com.zju.se.nohair.fitness.coach.controller;

import com.zju.se.nohair.fitness.coach.service.PrivateCourseService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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


}
