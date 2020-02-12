package com.zju.se.nohair.fitness.web.admin.controller;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCreateCustomerUserDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminCustomerUserService;
import com.zju.se.nohair.fitness.web.admin.service.AdminPrivateCourseService;
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
 * 后台模块的课程子模块下的私教课 Controller.
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/2/6 20:14
 */
@RestController
@RequestMapping("admin/course/private_course")
@Api
public class AdminPrivateCourseController {

  private static Logger logger = LoggerFactory.getLogger(AdminPrivateCourseController.class);

  @Autowired
  private AdminPrivateCourseService adminPrivateCourseService;

  @ApiOperation(value = "私教课列表", httpMethod = "GET")
  @RequestMapping(value = "list_all", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listAll() {
    BaseResult baseResult = adminPrivateCourseService.listAll();
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "私教课详情", httpMethod = "GET")
  @RequestMapping(value = "detail/{courseId}/{customerId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getDetailInfo(@PathVariable("courseId") Integer courseId,@PathVariable("customerId") Integer customerId) {
    BaseResult baseResult = adminPrivateCourseService.getDetailByIds(courseId,customerId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
