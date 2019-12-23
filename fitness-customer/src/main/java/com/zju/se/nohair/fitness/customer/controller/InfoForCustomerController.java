package com.zju.se.nohair.fitness.customer.controller;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.customer.service.CoachInCustomerService;
import com.zju.se.nohair.fitness.customer.service.GymInCustomerService;
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
 * 客户端需要的信息 包括健身房、教练等信息
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/23 16:05
 */

@RestController
@RequestMapping("customer/info")
@Api
public class InfoForCustomerController {

  private static Logger logger = LoggerFactory.getLogger(CourseController.class);

  @Autowired
  private CoachInCustomerService coachService;
  @Autowired
  private GymInCustomerService gymService;

  @ApiOperation(value = "查询教练详细信息", httpMethod = "GET")
  @RequestMapping(value = "details/coach/{coachId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getCoachDetail(@PathVariable("coachId") Integer coachId) {
    BaseResult baseResult = coachService.getCoachDetail(coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }


  @ApiOperation(value = "查询健身房详细信息", httpMethod = "GET")
  @RequestMapping(value = "details/gym/{gymId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getGymDetail(@PathVariable("gymId") Integer gymId) {
    BaseResult baseResult = gymService.getGymDetail(gymId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
