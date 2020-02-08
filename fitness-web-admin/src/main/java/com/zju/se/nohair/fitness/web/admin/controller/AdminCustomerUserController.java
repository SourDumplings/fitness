package com.zju.se.nohair.fitness.web.admin.controller;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCreateCustomerUserDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminCustomerUserService;
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
import org.springframework.web.multipart.MultipartFile;

/**
 * 后台模块的用户子模块下的教练用户 Controller.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/19 16:14
 */
@RestController
@RequestMapping("admin/user/customer")
@Api
public class AdminCustomerUserController {

  private static Logger logger = LoggerFactory.getLogger(AdminCustomerUserController.class);

  private AdminCustomerUserService adminCustomerUserService;

  @Autowired
  public void setAdminCustomerUserService(AdminCustomerUserService adminCustomerUserService) {
    this.adminCustomerUserService = adminCustomerUserService;
  }

  @ApiOperation(value = "顾客用户列表", httpMethod = "GET")
  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listAll() {
    BaseResult baseResult = adminCustomerUserService.listAll();
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "注册/更新顾客用户；id 为 -1 即为注册，否则为更新；性别 0 男 1 女；"
      + "生日字符串格式：2000/01/02", httpMethod = "POST")
  @RequestMapping(value = "", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> saveItem(
      AdminCreateCustomerUserDto adminCreateCustomerUserDto,
      @RequestParam("profilePic") MultipartFile profilePic) {
    BaseResult baseResult = adminCustomerUserService.saveItem(adminCreateCustomerUserDto,
        profilePic);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "删除顾客用户", httpMethod = "DELETE")
  @RequestMapping(value = "{customerId}", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity<Object> deleteItem(
      @PathVariable(value = "customerId") Integer customerId) {
    BaseResult baseResult = adminCustomerUserService.deleteItem(customerId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
