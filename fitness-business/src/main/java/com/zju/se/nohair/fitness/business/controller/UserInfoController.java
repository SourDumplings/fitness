package com.zju.se.nohair.fitness.business.controller;

import com.zju.se.nohair.fitness.business.dto.ChangePasswordDto;
import com.zju.se.nohair.fitness.business.dto.CreateBusinessUserDto;
import com.zju.se.nohair.fitness.business.dto.LoginDto;
import com.zju.se.nohair.fitness.business.service.UserInfoService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商家用户信息的 controller.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/17 19:46
 */
@RestController
@RequestMapping("business/user")
@Api
public class UserInfoController {

  private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);

  private UserInfoService userInfoService;

  @Autowired
  public void setUserInfoService(UserInfoService userInfoService) {
    this.userInfoService = userInfoService;
  }

  @ApiOperation(value = "注册新的商家用户", httpMethod = "POST")
  @RequestMapping(value = "", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> createPublicCourse(
      CreateBusinessUserDto createBusinessUserDto,
      @RequestParam("profilePic") MultipartFile profilePic,
      @RequestParam("certificationPic") MultipartFile certificationPic) {
    BaseResult baseResult = userInfoService.createBusinessUser(createBusinessUserDto,
        profilePic, certificationPic);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "商家用户上传头像图片", httpMethod = "PUT")
  @RequestMapping(value = "profile/{businessId}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Object> uploadProfilePic(
      @PathVariable("businessId") Integer businessId,
      @RequestParam("profilePic") MultipartFile profilePic) {
    BaseResult baseResult = userInfoService.uploadProfilePic(businessId, profilePic);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "商家用户上传资格证图片", httpMethod = "PUT")
  @RequestMapping(value = "certification/{businessId}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Object> uploadCertificationPic(
      @PathVariable("businessId") Integer businessId,
      @RequestParam("certificationPic") MultipartFile certificationPic) {
    BaseResult baseResult = userInfoService.uploadCertificationPic(businessId, certificationPic);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看商家用户详情", httpMethod = "GET")
  @RequestMapping(value = "{businessId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getPublicCourseDetailByCourseId(
      @PathVariable("businessId") Integer businessId) {
    BaseResult baseResult = userInfoService.getBusinessUserDetailByBusinessId(businessId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "商家用户登录", httpMethod = "POST")
  @RequestMapping(value = "login", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> getPublicCourseDetailByCourseId(
      @RequestBody LoginDto loginDto) {
    BaseResult baseResult = userInfoService.login(loginDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "商家用户修改密码", httpMethod = "PUT")
  @RequestMapping(value = "changePassword", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Object> changePasssword(
      @RequestBody ChangePasswordDto changePasswordDto) {
    BaseResult baseResult = userInfoService.changePasssword(changePasswordDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
