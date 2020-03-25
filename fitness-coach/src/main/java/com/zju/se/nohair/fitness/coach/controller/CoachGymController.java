package com.zju.se.nohair.fitness.coach.controller;

import com.zju.se.nohair.fitness.coach.service.GymService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健身房Controller
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-20
 */
@RestController
@RequestMapping("coach/gym")
@Api
public class CoachGymController {
  private static Logger logger = LoggerFactory.getLogger(CoachGymController.class);

  private GymService gymService;

  @Autowired
  public void setGymService(GymService gymService) {
    this.gymService = gymService;
  }

  @ApiOperation(value = "查看健身房详细信息", httpMethod = "GET")
  @RequestMapping(value = "{gymId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getGymDetail(
      @PathVariable("gymId") Integer gymId) {
    BaseResult baseResult = gymService.getGymDetail(gymId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "根据商家id查看健身房详细信息", httpMethod = "GET")
  @RequestMapping(value = "detail/{businessId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getGymDetailByBusinessId(
      @PathVariable("businessId") Integer businessId) {
    BaseResult baseResult = gymService.getGymDetailByBusinessId(businessId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看全部健身房列表", httpMethod = "GET")
  @RequestMapping(value = "gymList", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listGym() {
    BaseResult baseResult = gymService.listGym();
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
