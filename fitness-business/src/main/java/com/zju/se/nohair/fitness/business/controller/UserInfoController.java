package com.zju.se.nohair.fitness.business.controller;

import com.zju.se.nohair.fitness.business.dto.CreateBusinessUserDto;
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

/*  @ApiOperation(value = "更新商家用户信息", httpMethod = "PUT")
  @RequestMapping(value = "", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Object> createPublicCourse(
      @RequestBody @Valid UpdateBusinessUserDetailDto updateBusinessUserDetailDto,
      @RequestParam("profilePic") MultipartFile profilePic,
      @RequestParam("certificationPic") MultipartFile certificationPic) {
    BaseResult baseResult = userInfoService.updateBusinessUserDetail(updateBusinessUserDetailDto,
        profilePic, certificationPic);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }*/
}
