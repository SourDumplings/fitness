package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import com.zju.se.nohair.fitness.web.admin.dto.AdminBusinessUserListItemDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminBusinessUserService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 后台模块的用户子模块下的商家用户 Service 接口实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/16 9:37
 */
@Service
public class AdminBusinessUserServiceImpl implements AdminBusinessUserService {

  private static Logger logger = LoggerFactory.getLogger(AdminBusinessUserServiceImpl.class);

  private BusinessMapper businessMapper;

  @Autowired
  public void setBusinessMapper(BusinessMapper businessMapper) {
    this.businessMapper = businessMapper;
  }

  @Override
  public BaseResult listAll() {
    BaseResult res = null;

    try {
      final List<BusinessPo> businessPoList = businessMapper
          .selectAll();
      List<AdminBusinessUserListItemDto> adminBusinessUserListItemDtoList = new ArrayList<>();
      for (BusinessPo businessPo : businessPoList) {
        AdminBusinessUserListItemDto adminBusinessUserListItemDto = new AdminBusinessUserListItemDto();
        BeanUtils.copyProperties(businessPo, adminBusinessUserListItemDto);
        adminBusinessUserListItemDtoList.add(adminBusinessUserListItemDto);
      }
      res = BaseResult.success("查询课程列表成功");
      res.setData(adminBusinessUserListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询课程列表失败");
    }

    return res;
  }
}
