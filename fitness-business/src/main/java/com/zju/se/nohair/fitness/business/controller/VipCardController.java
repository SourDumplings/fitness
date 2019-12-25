package com.zju.se.nohair.fitness.business.controller;

import com.zju.se.nohair.fitness.business.dto.CreateVipCardDto;
import com.zju.se.nohair.fitness.business.service.VipCardService;
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
 * 商家对于会员卡的 Controller.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/24 16:55
 */
@RestController
@RequestMapping("business/vip_card")
@Api
public class VipCardController {

  private static Logger logger = LoggerFactory.getLogger(VipCardController.class);

  private VipCardService vipCardService;

  @Autowired
  public void setVipCardService(VipCardService vipCardService) {
    this.vipCardService = vipCardService;
  }

  @ApiOperation(value = "保存会员卡信息；会员卡类型（0代表年卡，1代表季卡，2代表月卡）；price 字段为0代表删除该卡", httpMethod = "PUT")
  @RequestMapping(value = "", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Object> saveCardService(
      @RequestBody @Valid CreateVipCardDto createVipCardDto) {
    BaseResult baseResult = vipCardService.saveCardService(createVipCardDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看发布的会员卡列表；会员卡类型（0代表年卡，1代表季卡，2代表月卡）", httpMethod = "GET")
  @RequestMapping(value = "{businessId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listVipCardInfo(
      @PathVariable("businessId") Integer businessId) {
    BaseResult baseResult = vipCardService.listVipCardInfo(businessId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
