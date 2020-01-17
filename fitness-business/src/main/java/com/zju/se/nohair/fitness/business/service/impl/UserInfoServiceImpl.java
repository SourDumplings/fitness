package com.zju.se.nohair.fitness.business.service.impl;

import com.zju.se.nohair.fitness.business.dto.BusinessUserDetailDto;
import com.zju.se.nohair.fitness.business.dto.CreateBusinessUserDto;
import com.zju.se.nohair.fitness.business.dto.UpdateBusinessUserDetailDto;
import com.zju.se.nohair.fitness.business.service.UserInfoService;
import com.zju.se.nohair.fitness.business.utils.PicUtils;
import com.zju.se.nohair.fitness.commons.constant.CertificationStatus;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import java.math.BigDecimal;
import java.util.Date;
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
 * 商家用户信息相关的服务接口实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/17 18:08
 */
@Transactional(readOnly = true)
@Service
public class UserInfoServiceImpl implements UserInfoService {

  private static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

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


  @Transactional(readOnly = false)
  @Override
  public BaseResult createBusinessUser(CreateBusinessUserDto createBusinessUserDto,
      MultipartFile profilePic, MultipartFile certificationPic) {
    BaseResult res = null;

    try {
      BusinessPo businessPo = new BusinessPo();
      BeanUtils.copyProperties(createBusinessUserDto, businessPo);
      businessPo.setPassword(DigestUtils.md5DigestAsHex(businessPo.getPassword().getBytes()));
      businessPo.setCreatedTime(new Date());
      businessPo.setStatus(CertificationStatus.NEW_PUBLISH);
      businessPo.setBalance(BigDecimal.ZERO);
      businessPo.setPicId(PicUtils.saveSinglePic(pictureMapper, profilePic));
      businessPo.setCertificationPicId(PicUtils.saveSinglePic(pictureMapper, certificationPic));
      businessMapper.insert(businessPo);
      res = BaseResult.success("商家用户注册成功");
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("商家用户注册失败");
    }

    return res;
  }

  @Override
  public BaseResult getBusinessUserDetailByBusinessId(Integer businessId) {
    BaseResult res = null;

    try {
      final BusinessPo businessPo = businessMapper.selectByPrimaryKey(businessId);
      BusinessUserDetailDto businessUserDetailDto = new BusinessUserDetailDto();
      BeanUtils.copyProperties(businessPo, businessUserDetailDto);
      businessUserDetailDto.setCertificationPicPath(
          pictureMapper.selectByPrimaryKey(businessPo.getCertificationPicId()).getPicLink());
      businessUserDetailDto
          .setPicPath(pictureMapper.selectByPrimaryKey(businessPo.getPicId()).getPicLink());
      res = BaseResult.success("查看商家用户详情成功");
      res.setData(businessUserDetailDto);
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("查看商家用户详情失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult updateBusinessUserDetail(
      UpdateBusinessUserDetailDto updateBusinessUserDetailDto,
      MultipartFile profilePic, MultipartFile certificationPic) {
    BaseResult res = null;

    try {
      BusinessPo businessPo = businessMapper
          .selectByPrimaryKey(updateBusinessUserDetailDto.getId());
      businessPo.setPassword(DigestUtils.md5DigestAsHex(businessPo.getPassword().getBytes()));
      businessPo.setUsername(updateBusinessUserDetailDto.getUsername());
      businessPo.setPhone(updateBusinessUserDetailDto.getPhone());
      businessMapper.updateByPrimaryKey(businessPo);
      res = BaseResult.success("商家用户信息修改成功");
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("商家用户信息修改失败");
    }

    return res;
  }
}
