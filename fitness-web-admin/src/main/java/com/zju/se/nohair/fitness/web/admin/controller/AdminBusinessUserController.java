package com.zju.se.nohair.fitness.web.admin.controller;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.web.admin.service.AdminBusinessUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台模块的用户子模块下的商家用户 Controller.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/16 9:47
 */
@RestController
@RequestMapping("admin/user/business")
@Api
public class AdminBusinessUserController {

  private static Logger logger = LoggerFactory.getLogger(AdminBusinessUserController.class);

  private AdminBusinessUserService adminBusinessUserService;

  @Autowired
  public void setAdminBusinessUserService(AdminBusinessUserService adminBusinessUserService) {
    this.adminBusinessUserService = adminBusinessUserService;
  }

  @ApiOperation(value = "商家用户列表；审核状态（0代表未审批，1代表已审批成功，2代表审批未通过）", httpMethod = "GET")
  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listAll() {
    BaseResult baseResult = adminBusinessUserService.listAll();
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
