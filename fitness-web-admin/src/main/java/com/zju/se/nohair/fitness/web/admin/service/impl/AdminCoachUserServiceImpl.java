package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.commons.constant.ReceiveRecordType;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.ReceiveRecordMapper;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.dao.po.ReceiveRecordPo;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCoachUserDetailDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCoachUserListItemDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminReceiveRecordListItemDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminCoachUserService;
import java.text.DateFormat;
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

  private ReceiveRecordMapper receiveRecordMapper;

  @Autowired
  public void setCoachMapper(CoachMapper coachMapper) {
    this.coachMapper = coachMapper;
  }

  @Autowired
  public void setReceiveRecordMapper(ReceiveRecordMapper receiveRecordMapper) {
    this.receiveRecordMapper = receiveRecordMapper;
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

  @Override
  public BaseResult getDetailByCoachId(Integer coachId) {
    BaseResult res = null;

    try {
      AdminCoachUserDetailDto adminCoachUserDetailDto = new AdminCoachUserDetailDto();
      final CoachPo coachPo = coachMapper.selectByPrimaryKey(coachId);
      BeanUtils.copyProperties(coachPo, adminCoachUserDetailDto);

      final List<ReceiveRecordPo> receiveRecordPos = receiveRecordMapper
          .selectAllCoachReceiveRecordsByCoachId(coachId);
      List<AdminReceiveRecordListItemDto> adminReceiveRecordListItemDtoList = new ArrayList<>();
      for (ReceiveRecordPo receiveRecordPo : receiveRecordPos) {
        AdminReceiveRecordListItemDto adminReceiveRecordListItemDto = new AdminReceiveRecordListItemDto();
        BeanUtils.copyProperties(receiveRecordPo, adminReceiveRecordListItemDto);

        String prefix = null;
        final Integer type = receiveRecordPo.getType();
        if (type.equals(ReceiveRecordType.PRIVATE_COURSE_FEE)) {
          prefix = "SF";
        } else if (type.equals(ReceiveRecordType.COACH_FEE)) {
          prefix = "SJ";
        }

        adminReceiveRecordListItemDto.setUserId(prefix + receiveRecordPo.getFromId());
        adminReceiveRecordListItemDto.setRecordNo(
            DateFormat.getDateInstance().format(receiveRecordPo.getCreatedTime())
                + "-" + receiveRecordPo.getId());
        adminReceiveRecordListItemDtoList.add(adminReceiveRecordListItemDto);
      }
      adminCoachUserDetailDto.setFinanceRecords(adminReceiveRecordListItemDtoList);
      res = BaseResult.success("查询教练列表成功");
      res.setData(adminCoachUserDetailDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询教练列表失败");
    }

    return res;
  }
}
