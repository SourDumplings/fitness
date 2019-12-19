package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.po.CustomerPo;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCustomerUserListItemDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminCustomerUserService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 后台模块的用户子模块下的顾客用户 Service 接口实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/19 16:06
 */
@Service
public class AdminCustomerUserServiceImpl implements AdminCustomerUserService {

  private static Logger logger = LoggerFactory.getLogger(AdminCustomerUserServiceImpl.class);

  private CustomerMapper customerMapper;

  @Autowired
  public void setCustomerMapper(CustomerMapper customerMapper) {
    this.customerMapper = customerMapper;
  }

  @Override
  public BaseResult listAll() {
    BaseResult res = null;

    try {
      final List<CustomerPo> customerPoList = customerMapper
          .selectAll();
      List<AdminCustomerUserListItemDto> adminCustomerUserListItemDtoList = new ArrayList<>();
      for (CustomerPo customerPo : customerPoList) {
        AdminCustomerUserListItemDto adminCustomerUserListItemDto = new AdminCustomerUserListItemDto();
        BeanUtils.copyProperties(customerPo, adminCustomerUserListItemDto);
        adminCustomerUserListItemDtoList.add(adminCustomerUserListItemDto);
      }
      res = BaseResult.success("查询顾客列表成功");
      res.setData(adminCustomerUserListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询顾客列表失败");
    }

    return res;
  }
}
