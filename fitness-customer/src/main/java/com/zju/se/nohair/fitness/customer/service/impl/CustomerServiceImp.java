package com.zju.se.nohair.fitness.customer.service.impl;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.customer.service.CustomerService;
import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.po.CustomerPo;
import com.zju.se.nohair.fitness.dao.po.TakesPublicPoKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/28 18:12
 */
@Service
public class CustomerServiceImp implements CustomerService {

  private static Logger logger = LoggerFactory.getLogger(CustomerServiceImp.class);
  @Autowired
  private CustomerMapper customerMapper;

  @Override
  public BaseResult getCustomerBalance(Integer customerId) {
    BaseResult res = null;

    try {

      CustomerPo customerPo = customerMapper.selectByPrimaryKey(customerId);
      res = BaseResult.success("查询成功");
      res.setMessage(customerPo.getBalance().toString());

    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询失败");
    }

    return res;
  }
}
