package com.zju.se.nohair.fitness.coach.service.impl;

import com.zju.se.nohair.fitness.coach.dto.ChangeCoachDetailDto;
import com.zju.se.nohair.fitness.coach.dto.ChangeCoachPasswordDto;
import com.zju.se.nohair.fitness.coach.dto.CoachDetailDto;
import com.zju.se.nohair.fitness.coach.dto.CreateCoachDto;
import com.zju.se.nohair.fitness.coach.service.DetailService;
import com.zju.se.nohair.fitness.coach.utils.PicUtils;
import com.zju.se.nohair.fitness.commons.constant.CertificationStatus;
import com.zju.se.nohair.fitness.commons.constant.ResponseStatus;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.dao.po.PicturePo;
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

      if (coachPo.getPicId() != null) {
        final PicturePo picturePo = pictureMapper.selectByPrimaryKey(coachPo.getPicId());
        coachDetailDto.setPicId(picturePo.getPicLink());
      } else {
        coachDetailDto.setPicId(null);
      }

      if (coachPo.getCertificationPicId() != null) {
        final PicturePo picturePo = pictureMapper.selectByPrimaryKey(coachPo.getCertificationPicId());
        coachDetailDto.setCertificationPicId(picturePo.getPicLink());
      } else {
        coachDetailDto.setCertificationPicId(null);
      }
      res = BaseResult.success("查看教练的个人信息成功");
      res.setData(coachDetailDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查看教练的个人信息失败");
    }

    return res;
  }

  @Override
  public BaseResult logInCoach(String username,String password) {
    //教练端登录
    BaseResult res = null;

    try {
      String passwordFromSQL = coachMapper.selectByUsername(username);

      String passwordMd5 = DigestUtils.md5DigestAsHex(password.getBytes());
      //System.out.println(passwordMd5);
      if(passwordMd5.equals(passwordFromSQL)){
        CoachPo coachPo = coachMapper.selectCoachIdByUsername(username);//教练端登录成功返回CoachID
        //System.out.println(coachId);
        res = BaseResult.success("教练端登录成功");
        res.setData(coachPo.getId());
      }else{
        res = BaseResult.fail("用户名或密码错误");
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("用户名或密码错误");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult changeCoachDetail(ChangeCoachDetailDto changeCoachDetailDto) {
    //教练端修个人信息
    BaseResult res = null;

    try {
      final Integer coachId = changeCoachDetailDto.getId();
      final CoachPo coachPo = coachMapper.selectByPrimaryKey(coachId);
      CoachDetailDto coachDetailDto = new CoachDetailDto();
      coachPo.setAdeptness(changeCoachDetailDto.getAdeptness());
      coachPo.setGender(changeCoachDetailDto.getGender());
      coachPo.setPhone(changeCoachDetailDto.getPhone());
      coachPo.setBirthday(changeCoachDetailDto.getBirthday());
      coachPo.setPs(changeCoachDetailDto.getPs());
      coachPo.setHeight(changeCoachDetailDto.getHeight());
      coachPo.setWeight(changeCoachDetailDto.getWeight());
      BeanUtils.copyProperties(coachPo,coachDetailDto);
      coachMapper.updateByPrimaryKey(coachPo);
      res = BaseResult.success("教练端修改个人信息成功");

    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("教练端修改个人信息失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult changeCoachPassword(ChangeCoachPasswordDto changeCoachPasswordDto) {
    //教练端修改密码
    BaseResult res = null;

    try {
      final Integer coachId = changeCoachPasswordDto.getId();
      final CoachPo coachPo = coachMapper.selectByPrimaryKey(coachId);
      String passwordMd5 = DigestUtils.md5DigestAsHex(changeCoachPasswordDto.getPassword().getBytes());

      if(coachPo.getPassword().equals(passwordMd5)){//原密码与数据库中密码一致
        CoachDetailDto coachDetailDto = new CoachDetailDto();

        if(changeCoachPasswordDto.getPassword1().equals(changeCoachPasswordDto.getPassword2())){//两次输入密码一致
          String password1Md5 = DigestUtils.md5DigestAsHex(changeCoachPasswordDto.getPassword1().getBytes());
          coachPo.setPassword(password1Md5);
          BeanUtils.copyProperties(coachPo,coachDetailDto);
          coachMapper.updateByPrimaryKey(coachPo);
          res = BaseResult.success("教练端修改密码成功");
        }else{
          res = BaseResult.fail("两次输入密码不一致！");
        }

      }else{
        res = BaseResult.fail("原密码输入错误！");
      }


    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("教练端修改密码失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult createBusinessUser(CreateCoachDto createCoachDto,MultipartFile certificationPic) {
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
      coachPo.setCertificationPicId(PicUtils.saveSinglePic(pictureMapper, certificationPic));
      coachPo.setPicId(1);
      coachMapper.insertReturnId(coachPo);

      res = BaseResult.success("新教练注册成功");
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("新教练用户注册失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult createCoachProfilePic(Integer coachId, MultipartFile profilePic) {
    //上传头像
    BaseResult res = null;

    try {
      final CoachPo coachPo = coachMapper.selectByPrimaryKey(coachId);
      CoachDetailDto coachDetailDto = new CoachDetailDto();

      coachPo.setPicId(PicUtils.saveSinglePic(pictureMapper, profilePic));
      BeanUtils.copyProperties(coachPo,coachDetailDto);
      coachMapper.updateByPrimaryKey(coachPo);
      res = BaseResult.success("上传头像成功");
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("上传头像失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult createCoachCertificationPic(Integer coachId, MultipartFile certificationPic) {
    //上传资格证
    BaseResult res = null;

    try {
      final CoachPo coachPo = coachMapper.selectByPrimaryKey(coachId);
      CoachDetailDto coachDetailDto = new CoachDetailDto();

      coachPo.setCertificationPicId(PicUtils.saveSinglePic(pictureMapper, certificationPic));
      BeanUtils.copyProperties(coachPo,coachDetailDto);
      coachMapper.updateByPrimaryKey(coachPo);
      res = BaseResult.success("上传资格证成功");
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("上传资格证失败");
    }

    return res;
  }


}
