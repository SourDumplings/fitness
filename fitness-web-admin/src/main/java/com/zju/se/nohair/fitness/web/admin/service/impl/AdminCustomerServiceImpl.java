package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.po.Customer;
import com.zju.se.nohair.fitness.web.admin.service.AdminCustomerService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Web 后台的顾客管理服务实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/9 20:53
 */
@Service
public class AdminCustomerServiceImpl implements AdminCustomerService {

  private static Logger logger = LoggerFactory.getLogger(AdminCustomerServiceImpl.class);

  private CustomerMapper customerMapper;

  @Autowired
  public void setUserMapper(CustomerMapper customerMapper) {
    this.customerMapper = customerMapper;
  }

  @Override
  public List<Customer> listAll() {
    return customerMapper.selectAll();
  }
}
