package com.zju.se.nohair.fitness.coach.controller;

import com.zju.se.nohair.fitness.coach.service.DetailService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
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
 * 教练端-我的Controller
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-17
 */
@RestController
@RequestMapping("coach/detail")
@Api
public class CoachDetailController {
  private static Logger logger = LoggerFactory.getLogger(CoachDetailController.class);

  private DetailService detailService;

  @Autowired
  public void setFinanceService(DetailService detailService) {
    this.detailService = detailService;
  }

  @ApiOperation(value = "查看教练的个人信息", httpMethod = "GET")
  @RequestMapping(value = "{coachId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getCoachDetailByCoachId(
      @PathVariable("coachId") Integer coachId) {
    BaseResult baseResult = detailService.getCoachDetailByCoachId(coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

}