package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.commons.constant.CertificationStatus;
import com.zju.se.nohair.fitness.commons.constant.ReceiveRecordType;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.commons.utils.DateUtils;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.mapper.ReceiveRecordMapper;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.dao.po.ReceiveRecordPo;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCoachUserDetailDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCoachUserListItemDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCreateCoachUserDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminReceiveRecordListItemDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminCoachUserService;
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
 * 后台模块的用户子模块下的教练用户 Service 接口实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/19 15:15
 */
@Transactional(readOnly = true)
@Service
public class AdminCoachUserServiceImpl implements AdminCoachUserService {

  private static Logger logger = LoggerFactory.getLogger(AdminCoachUserServiceImpl.class);

  private CoachMapper coachMapper;

  private ReceiveRecordMapper receiveRecordMapper;

  private PictureMapper pictureMapper;

  @Autowired
  public void setCoachMapper(CoachMapper coachMapper) {
    this.coachMapper = coachMapper;
  }

  @Autowired
  public void setReceiveRecordMapper(ReceiveRecordMapper receiveRecordMapper) {
    this.receiveRecordMapper = receiveRecordMapper;
  }

  @Autowired
  public void setPictureMapper(PictureMapper pictureMapper) {
    this.pictureMapper = pictureMapper;
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
        adminCoachUserListItemDto.setAge(DateUtils.getAgeFromBirthday(coachPo.getBirthday()));
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
  public BaseResult getDetailById(Integer coachId) {
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
        final Integer receiveRecordType = receiveRecordPo.getType();
        if (receiveRecordType.equals(ReceiveRecordType.PRIVATE_COURSE_FEE)) {
          adminReceiveRecordListItemDto.setFromUserTypeName("顾客");
        } else if (receiveRecordType.equals(ReceiveRecordType.COACH_FEE)) {
          adminReceiveRecordListItemDto.setFromUserTypeName("商家");
        }

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

  @Transactional(readOnly = false)
  @Override
  public BaseResult saveItem(AdminCreateCoachUserDto adminCreateCoachUserDto,
      MultipartFile profilePic, MultipartFile certificationPic) {
    BaseResult res = null;

    try {
      boolean isCreating = false;
      if (adminCreateCoachUserDto.getId() == -1) {
        isCreating = true;
        adminCreateCoachUserDto.setId(null);
      }

      CoachPo coachPo = new CoachPo();
      BeanUtils.copyProperties(adminCreateCoachUserDto, coachPo);
      coachPo.setPassword(DigestUtils.md5DigestAsHex(coachPo.getPassword().getBytes()));
      coachPo.setStatus(CertificationStatus.NEW_PUBLISH);
      coachPo.setBalance(BigDecimal.ZERO);
      coachPo.setPicId(PicUtils.saveSinglePic(pictureMapper, profilePic));
      coachPo.setCertificationPicId(PicUtils.saveSinglePic(pictureMapper, certificationPic));

      if (isCreating) {
        coachPo.setCreatedTime(new Date());
        coachMapper.insert(coachPo);
        res = BaseResult.success("教练用户注册成功");
      } else {
        final CoachPo target = coachMapper
            .selectByPrimaryKey(adminCreateCoachUserDto.getId());
        if (target == null) {
          res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "无此 id 的教练用户");
        } else {
          coachPo.setCreatedTime(target.getCreatedTime());
          coachMapper.updateByPrimaryKey(coachPo);
          res = BaseResult.success("教练信息更新成功");
        }
      }

    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("教练用户注册/更新失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult deleteItem(Integer id) {
    BaseResult res = null;

    try {
      coachMapper.deleteByPrimaryKey(id);
      res = BaseResult.success("删除教练用户成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("删除教练用户失败");
    }

    return res;
  }
}
