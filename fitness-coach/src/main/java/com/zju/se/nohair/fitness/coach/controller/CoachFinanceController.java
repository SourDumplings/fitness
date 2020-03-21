package com.zju.se.nohair.fitness.coach.controller;

import com.zju.se.nohair.fitness.coach.service.FinanceService;
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
 * 教练钱包Controller
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-15
 */
@RestController
@RequestMapping("coach/finance")
@Api
public class CoachFinanceController {
  private static Logger logger = LoggerFactory.getLogger(CoachFinanceController.class);

  private FinanceService financeService;

  @Autowired
  public void setFinanceService(FinanceService financeService) {
    this.financeService = financeService;
  }

  @ApiOperation(value = "查询教练端交易记录的列表；"
      + "转账类型 type（0代表私教课程费，1代表团课课程费，2代表场地费， 3代表教练费，4代表会员卡费）；"
      + "教练作为收款方金额是正数，付款方时金额为负数；"
      + "otherId 是指交易对方的 id，name 是指对方的 username（顾客），person_name（商家）", httpMethod = "GET")
  @RequestMapping(value = "record/{coachId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listFinanceRecordsByCoachId(
      @PathVariable(name = "coachId") Integer coachId) {
    BaseResult baseResult = financeService.listFinanceRecordsByCoachId(coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }


  @ApiOperation(value = "查看教练钱包余额", httpMethod = "GET")
  @RequestMapping(value = "{coachId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getBalanceByCoachId(
      @PathVariable(name = "coachId") Integer coachId) {
    BaseResult baseResult = financeService.getBalanceByCoachId(coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
