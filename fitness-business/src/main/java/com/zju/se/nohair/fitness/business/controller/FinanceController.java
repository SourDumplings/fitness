package com.zju.se.nohair.fitness.business.controller;

import com.zju.se.nohair.fitness.business.service.FinanceService;
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
 * 商家金融相关 Controller.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/28 23:20
 */
@RestController
@RequestMapping("business/finance")
@Api
public class FinanceController {

  private static Logger logger = LoggerFactory.getLogger(FinanceController.class);

  private FinanceService financeService;

  @Autowired
  public void setFinanceService(FinanceService financeService) {
    this.financeService = financeService;
  }

  @ApiOperation(value = "查询商家交易记录的列表；"
      + "转账类型 type（0代表私教课程费，1代表团课课程费，2代表场地费， 3代表教练费，4代表会员卡费）；"
      + "商家作为收款方金额是正数，付款方时金额为负数；"
      + "otherId 是指交易对方的 id，name 是指对方的用户名（顾客），姓名（教练）", httpMethod = "GET")
  @RequestMapping(value = "record/{businessId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listFinanceRecordsByBusinessId(
      @PathVariable(name = "businessId") Integer businessId) {
    BaseResult baseResult = financeService.listFinanceRecordsByBusinessId(businessId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查询商家钱包余额", httpMethod = "GET")
  @RequestMapping(value = "{businessId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getBalanceByBusinessId(
      @PathVariable(name = "businessId") Integer businessId) {
    BaseResult baseResult = financeService.getBalanceByBusinessId(businessId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
