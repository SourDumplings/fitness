package com.zju.se.nohair.fitness.web.admin.controller;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.po.UserPo;
import com.zju.se.nohair.fitness.web.admin.dto.CreateUserDto;
import com.zju.se.nohair.fitness.web.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户 Controller 类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/21 11:50
 */
@RestController
@RequestMapping("admin/users")
@Api
public class UserController {

  private static Logger logger = LoggerFactory.getLogger(UserController.class);

  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @ApiOperation(value = "hello world 测试", httpMethod = "GET")
  @RequestMapping(value = "hello", method = RequestMethod.GET)
  public String sayHi() {
    logger.warn("say hihihihihi..................");
    return "hello";
  }

  @ApiOperation(value = "数据 mapper 测试", httpMethod = "GET")
  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public List<UserPo> listAll() {
    return userService.listAll();
  }

  @ApiOperation(value = "根据手机号获取用户信息", httpMethod = "GET")
  @RequestMapping(value = "phone", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getUserByPhone(@RequestParam(name = "phone") String phone) {
    BaseResult baseResult = userService.getUserByPhone(phone);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "创建新用户", httpMethod = "POST")
  @RequestMapping(value = "", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> createUser(@RequestBody @Valid CreateUserDto createUserDto) {
    BaseResult baseResult = userService.createUser(createUserDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
