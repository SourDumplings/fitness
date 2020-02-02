package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.commons.constant.CertificationStatus;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import com.zju.se.nohair.fitness.web.admin.dto.AdminBusinessUserListItemDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCreateBusinessUserDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminBusinessUserService;
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
 * 后台模块的用户子模块下的商家用户 Service 接口实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/16 9:37
 */
@Transactional(readOnly = true)
@Service
public class AdminBusinessUserServiceImpl implements AdminBusinessUserService {

  private static Logger logger = LoggerFactory.getLogger(AdminBusinessUserServiceImpl.class);

  private BusinessMapper businessMapper;

  private PictureMapper pictureMapper;

  @Autowired
  public void setBusinessMapper(BusinessMapper businessMapper) {
    this.businessMapper = businessMapper;
  }

  @Autowired
  public void setPictureMapper(PictureMapper pictureMapper) {
    this.pictureMapper = pictureMapper;
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
      res = BaseResult.success("查询商家列表成功");
      res.setData(adminBusinessUserListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询商家列表失败");
    }

    return res;
  }

  @Override
  public BaseResult getDetailById(Integer id) {
    return null;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult saveItem(AdminCreateBusinessUserDto adminCreateBusinessUserDto,
      MultipartFile profilePic, MultipartFile certificationPic) {
    BaseResult res = null;

    try {
      boolean isCreating = false;
      if (adminCreateBusinessUserDto.getId() == -1) {
        isCreating = true;
        adminCreateBusinessUserDto.setId(null);
      }

      BusinessPo businessPo = new BusinessPo();
      BeanUtils.copyProperties(adminCreateBusinessUserDto, businessPo);
      businessPo.setPassword(DigestUtils.md5DigestAsHex(businessPo.getPassword().getBytes()));
      businessPo.setStatus(CertificationStatus.NEW_PUBLISH);
      businessPo.setBalance(BigDecimal.ZERO);
      businessPo.setPicId(PicUtils.saveSinglePic(pictureMapper, profilePic));
      businessPo.setCertificationPicId(PicUtils.saveSinglePic(pictureMapper, certificationPic));

      if (isCreating) {
        businessPo.setCreatedTime(new Date());
        businessMapper.insert(businessPo);
        res = BaseResult.success("商家用户注册成功");
      } else {
        final BusinessPo target = businessMapper
            .selectByPrimaryKey(adminCreateBusinessUserDto.getId());
        if (target == null) {
          res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "无此 id 的商家用户");
        } else {
          businessPo.setCreatedTime(target.getCreatedTime());
          businessMapper.updateByPrimaryKey(businessPo);
          res = BaseResult.success("商家信息更新成功");
        }
      }

    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("商家用户注册/更新失败");
    }

    return res;
  }

  @Override
  public BaseResult deleteItem(Integer id) {
    return null;
  }
}
