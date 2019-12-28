package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.web.admin.service.AdminCoachUserService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCoachUserListItemDto;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 后台模块的用户子模块下的教练用户 Service 接口实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/19 15:15
 */
@Service
public class AdminCoachUserServiceImpl implements AdminCoachUserService {

  private static Logger logger = LoggerFactory.getLogger(AdminCoachUserServiceImpl.class);

  private CoachMapper coachMapper;

  @Autowired
  public void setCoachMapper(CoachMapper coachMapper) {
    this.coachMapper = coachMapper;
  }

  @Override
  public BaseResult listAll() {
    BaseResult res = null;

    try {
      final List<CoachPo> coachPoList = coachMapper
          .selectAll();
      List<AdminCoachUserListItemDto> adminCoachUserListItemDtoList = new ArrayList<>();
      for (CoachPo coachPo : coachPoList) {
        AdminCoachUserListItemDto adminCoachUserListItemDto = new AdminCoachUserListItemDto();
        BeanUtils.copyProperties(coachPo, adminCoachUserListItemDto);
        adminCoachUserListItemDtoList.add(adminCoachUserListItemDto);
      }
      res = BaseResult.success("查询教练列表成功");
      res.setData(adminCoachUserListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询教练列表失败");
    }

    return res;
  }
}
