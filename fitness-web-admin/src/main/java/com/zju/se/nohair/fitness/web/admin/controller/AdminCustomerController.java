package com.zju.se.nohair.fitness.web.admin.controller;

import com.zju.se.nohair.fitness.dao.po.CustomerPo;
import com.zju.se.nohair.fitness.web.admin.service.AdminCustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Web 后台的顾客管理 Controller.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/9 20:58
 */
@Controller
@RequestMapping("admin/customers")
@Api
public class AdminCustomerController {

  private static Logger logger = LoggerFactory.getLogger(AdminCustomerController.class);

  private AdminCustomerService adminCustomerService;

  @Autowired
  public void setAdminCustomerService(AdminCustomerService adminCustomerService) {
    this.adminCustomerService = adminCustomerService;
  }

  @ApiOperation(value = "获取顾客列表（测试用）", httpMethod = "GET")
  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public List<CustomerPo> listAll() {
    return adminCustomerService.listAll();
  }
}
