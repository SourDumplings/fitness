package com.zju.se.nohair.fitness.web.admin.controller;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCreateCoachUserDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminCoachUserService;
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
 * @date 2019/12/19 15:13
 */
@RestController
@RequestMapping("admin/user/coach")
@Api
public class AdminCoachUserController {

  private static Logger logger = LoggerFactory.getLogger(AdminCoachUserController.class);

  private AdminCoachUserService adminCoachUserService;

  @Autowired
  public void setAdminCoachUserService(AdminCoachUserService adminCoachUserService) {
    this.adminCoachUserService = adminCoachUserService;
  }

  @ApiOperation(value = "教练用户列表；审核状态（0代表未审批，1代表已审批成功，2代表审批未通过）", httpMethod = "GET")
  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listAll() {
    BaseResult baseResult = adminCoachUserService.listAll();
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "教练用户详情；审核状态（0代表未审批，1代表已审批成功，2代表审批未通过）", httpMethod = "GET")
  @RequestMapping(value = "{coachId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getDetailById(
      @PathVariable(value = "coachId") Integer coachId) {
    BaseResult baseResult = adminCoachUserService.getDetailById(coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "注册/更新教练用户；id 为 -1 即为注册，否则为更新；性别 0 男 1 女", httpMethod = "POST")
  @RequestMapping(value = "", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> saveItem(
      AdminCreateCoachUserDto adminCreateCoachUserDto,
      @RequestParam("profilePic") MultipartFile profilePic,
      @RequestParam("certificationPic") MultipartFile certificationPic) {
    BaseResult baseResult = adminCoachUserService.saveItem(adminCreateCoachUserDto,
        profilePic, certificationPic);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
