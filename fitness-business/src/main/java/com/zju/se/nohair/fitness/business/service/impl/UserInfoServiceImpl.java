package com.zju.se.nohair.fitness.business.service.impl;

import com.zju.se.nohair.fitness.business.dto.BusinessUserDetailDto;
import com.zju.se.nohair.fitness.business.dto.ChangePasswordDto;
import com.zju.se.nohair.fitness.business.dto.CreateBusinessUserDto;
import com.zju.se.nohair.fitness.business.dto.LoginDto;
import com.zju.se.nohair.fitness.business.service.UserInfoService;
import com.zju.se.nohair.fitness.business.utils.PicUtils;
import com.zju.se.nohair.fitness.commons.constant.CertificationStatus;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.commons.utils.DateUtils;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.GymMapper;
import com.zju.se.nohair.fitness.dao.mapper.OwnsGymMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import com.zju.se.nohair.fitness.dao.po.GymPo;
import com.zju.se.nohair.fitness.dao.po.OwnsGymPoKey;
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

  private OwnsGymMapper ownsGymMapper;

  private GymMapper gymMapper;

  @Autowired
  public void setBusinessMapper(BusinessMapper businessMapper) {
    this.businessMapper = businessMapper;
  }

  @Autowired
  public void setPictureMapper(PictureMapper pictureMapper) {
    this.pictureMapper = pictureMapper;
  }

  @Autowired
  public void setOwnsGymMapper(OwnsGymMapper ownsGymMapper) {
    this.ownsGymMapper = ownsGymMapper;
  }

  @Autowired
  public void setGymMapper(GymMapper gymMapper) {
    this.gymMapper = gymMapper;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult createBusinessUser(CreateBusinessUserDto createBusinessUserDto,
      MultipartFile profilePic, MultipartFile certificationPic) {
    BaseResult res = null;

    try {
      final Date now = new Date();

      BusinessPo businessPo = new BusinessPo();
      BeanUtils.copyProperties(createBusinessUserDto, businessPo);
      businessPo.setPassword(DigestUtils.md5DigestAsHex(businessPo.getPassword().getBytes()));
      businessPo.setCreatedTime(now);
      businessPo.setStatus(CertificationStatus.NEW_PUBLISH);
      businessPo.setBalance(BigDecimal.ZERO);
      businessPo.setPicId(PicUtils.saveSinglePic(pictureMapper, profilePic));
      businessPo.setCertificationPicId(PicUtils.saveSinglePic(pictureMapper, certificationPic));
      businessMapper.insertReturnId(businessPo);

      GymPo gymPo = new GymPo();
      gymPo.setName(createBusinessUserDto.getGymName());
      gymPo.setAddress(createBusinessUserDto.getAddress());
      gymPo.setBusinessId(businessPo.getId());
      gymPo.setCreatedTime(now);
      gymMapper.insertReturnId(gymPo);

      OwnsGymPoKey ownsGymPoKey = new OwnsGymPoKey();
      ownsGymPoKey.setBusinessId(businessPo.getId());
      ownsGymPoKey.setGymId(gymPo.getId());
      ownsGymMapper.insert(ownsGymPoKey);

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
      businessUserDetailDto.setCreatedTime(DateUtils.date2String(businessPo.getCreatedTime()));
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

  @Override
  public BaseResult login(LoginDto loginDto) {
    BaseResult res = null;

    try {
      BusinessPo businessPo = businessMapper.selectByUsername(loginDto.getUsername());
      String passwordMd5 = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
      if (businessPo != null && passwordMd5.equals(businessPo.getPassword())) {
        res = BaseResult.success("商家用户登录成功");
      } else {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "用户名或密码错误");
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("商家用户登录失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult changePasssword(ChangePasswordDto changePasswordDto) {
    BaseResult res = null;

    try {
      BusinessPo businessPo = businessMapper.selectByUsername(changePasswordDto.getUsername());
      String oldPassword = DigestUtils
          .md5DigestAsHex(changePasswordDto.getOldPassword().getBytes());
      if (businessPo != null && oldPassword.equals(businessPo.getPassword())) {
        businessPo
            .setPassword(DigestUtils.md5DigestAsHex(changePasswordDto.getNewPassword().getBytes()));
        businessMapper.updateByPrimaryKey(businessPo);
        res = BaseResult.success("商家端修改密码成功");
      } else {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "用户名或密码错误");
      }
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("商家端修改密码失败");
    }

    return res;
  }
}
