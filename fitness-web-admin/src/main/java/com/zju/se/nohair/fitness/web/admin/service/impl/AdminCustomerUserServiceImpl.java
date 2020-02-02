package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.po.CustomerPo;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCreateCustomerUserDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCustomerUserListItemDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminCustomerUserService;
import com.zju.se.nohair.fitness.web.admin.utils.PicUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 后台模块的用户子模块下的顾客用户 Service 接口实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/19 16:06
 */
@Transactional(readOnly = true)
@Service
public class AdminCustomerUserServiceImpl implements AdminCustomerUserService {

  private static Logger logger = LoggerFactory.getLogger(AdminCustomerUserServiceImpl.class);

  private CustomerMapper customerMapper;

  private PictureMapper pictureMapper;

  @Autowired
  public void setCustomerMapper(CustomerMapper customerMapper) {
    this.customerMapper = customerMapper;
  }

  @Autowired
  public void setPictureMapper(PictureMapper pictureMapper) {
    this.pictureMapper = pictureMapper;
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

  @Override
  public BaseResult getDetailById(Integer id) {
    return null;
  }

  @Override
  public BaseResult saveItem(AdminCreateCustomerUserDto adminCreateCustomerUserDto,
      MultipartFile profilePic) {
    BaseResult res = null;

    try {
      boolean isCreating = false;
      if (adminCreateCustomerUserDto.getId() == -1) {
        isCreating = true;
        adminCreateCustomerUserDto.setId(null);
      }

      CustomerPo customerPo = new CustomerPo();
      BeanUtils.copyProperties(adminCreateCustomerUserDto, customerPo);
      customerPo.setPassword(DigestUtils.md5DigestAsHex(customerPo.getPassword().getBytes()));
      customerPo.setBalance(BigDecimal.ZERO);
      customerPo.setPicId(PicUtils.saveSinglePic(pictureMapper, profilePic));

      if (isCreating) {
        customerPo.setCreatedTime(new Date());
        customerMapper.insert(customerPo);
        res = BaseResult.success("顾客用户注册成功");
      } else if (customerMapper.selectByPrimaryKey(adminCreateCustomerUserDto.getId()) == null) {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "无此 id 的顾客用户");
      } else {
        customerMapper.updateByPrimaryKey(customerPo);
        res = BaseResult.success("顾客信息更新成功");
      }

    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("顾客用户注册/更新失败");
    }

    return res;
  }

  @Override
  public BaseResult deleteItem(Integer id) {
    return null;
  }
}
