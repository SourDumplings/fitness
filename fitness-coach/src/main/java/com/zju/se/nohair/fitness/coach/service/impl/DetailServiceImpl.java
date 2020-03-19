package com.zju.se.nohair.fitness.coach.service.impl;

import com.zju.se.nohair.fitness.coach.dto.CoachDetailDto;
import com.zju.se.nohair.fitness.coach.dto.CreateCoachDto;
import com.zju.se.nohair.fitness.coach.service.DetailService;
import com.zju.se.nohair.fitness.coach.utils.PicUtils;
import com.zju.se.nohair.fitness.commons.constant.CertificationStatus;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
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
 * 教练端-我的-接口实现类
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-17
 */
@Transactional(readOnly = true)
@Service
public class DetailServiceImpl implements DetailService {

  private static Logger logger = LoggerFactory.getLogger(DetailServiceImpl.class);

  private CoachMapper coachMapper;

  private PictureMapper pictureMapper;

  @Autowired
  public void setCoachMapper(CoachMapper coachMapper) {
    this.coachMapper = coachMapper;
  }

  @Autowired
  public void setPictureMapper(PictureMapper pictureMapper) {
    this.pictureMapper = pictureMapper;
  }

  @Override
  public BaseResult getCoachDetailByCoachId(Integer coachId) {
    //查看教练的个人信息
    BaseResult res = null;

    try {
      final CoachPo coachPo = coachMapper.selectByPrimaryKey(coachId);
      CoachDetailDto coachDetailDto = new CoachDetailDto();
      BeanUtils.copyProperties(coachPo, coachDetailDto);
      res = BaseResult.success("查看教练的个人信息成功");
      res.setData(coachDetailDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查看教练的个人信息失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult createBusinessUser(CreateCoachDto createCoachDto) {
    //注册新教练
    BaseResult res = null;

    try {
      final Date now = new Date();

      CoachPo coachPo = new CoachPo();
      BeanUtils.copyProperties(createCoachDto, coachPo);
      coachPo.setPassword(DigestUtils.md5DigestAsHex(coachPo.getPassword().getBytes()));
      coachPo.setCreatedTime(now);
      coachPo.setStatus(CertificationStatus.NEW_PUBLISH);
      coachPo.setBalance(BigDecimal.ZERO);
      //coachPo.setPicId(PicUtils.saveSinglePic(pictureMapper, profilePic));
      //coachPo.setCertificationPicId(PicUtils.saveSinglePic(pictureMapper, certificationPic));
      coachPo.setPicId(1);
      coachPo.setCertificationPicId(1);
      coachMapper.insertReturnId(coachPo);

      res = BaseResult.success("新教练注册成功");
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("新教练用户注册失败");
    }

    return res;
  }
}
