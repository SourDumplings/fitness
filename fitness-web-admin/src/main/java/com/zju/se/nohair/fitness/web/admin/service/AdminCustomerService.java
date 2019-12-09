package com.zju.se.nohair.fitness.web.admin.service;

import com.zju.se.nohair.fitness.dao.po.Customer;
import java.util.List;

/**
 * Web 后台的顾客管理服务接口.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/9 20:52
 */
public interface AdminCustomerService {

  List<Customer> listAll();
}
