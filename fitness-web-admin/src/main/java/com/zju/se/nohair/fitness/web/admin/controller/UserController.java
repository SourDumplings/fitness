package com.zju.se.nohair.fitness.web.admin.controller;

import com.zju.se.nohair.fitness.web.admin.dao.po.User;
import com.zju.se.nohair.fitness.web.admin.service.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class UserController {

  private static Logger logger =  LoggerFactory.getLogger(UserController.class);

  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "hello", method = RequestMethod.GET)
  public String sayHi() {
    logger.warn("say hihihihihi..................");
    return "hello";
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public List<User> listAll() {
    return userService.listAll();
  }
}
