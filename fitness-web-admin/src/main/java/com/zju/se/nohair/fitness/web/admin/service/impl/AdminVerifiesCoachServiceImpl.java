package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.mapper.VerifiesMapper;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.dao.po.VerifiesPo;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCreateVerifiesDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminVerifiesCoachDetailDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminVerifiesCoachListDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminVerifiesCoachService;
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

/**
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-22
 */
@Transactional(readOnly = true)
@Service
public class AdminVerifiesCoachServiceImpl implements AdminVerifiesCoachService {

  private static Logger logger = LoggerFactory.getLogger(AdminBusinessUserServiceImpl.class);

  private BusinessMapper businessMapper;

  private PictureMapper pictureMapper;

  private CoachMapper coachMapper;

  private VerifiesMapper verifiesMapper;

  @Autowired
  public void setVerifiesMapper(VerifiesMapper verifiesMapper) { this.verifiesMapper = verifiesMapper; }

  @Autowired
  public void setCoachMapper(CoachMapper coachMapper) {
    this.coachMapper = coachMapper;
  }

  @Autowired
  public void setBusinessMapper(BusinessMapper businessMapper) { this.businessMapper = businessMapper; }

  @Autowired
  public void setPictureMapper(PictureMapper pictureMapper) {
    this.pictureMapper = pictureMapper;
  }

  @Override
  public BaseResult listCoachAll() {
    //教练审批列表
    BaseResult res = null;

    try {
      final List<CoachPo> coachPoList = coachMapper.selectAll();
      List<AdminVerifiesCoachListDto> adminVerifiesCoachListDtoList = new ArrayList<>();
      for (CoachPo coachPo : coachPoList) {
        AdminVerifiesCoachListDto adminVerifiesCoachListDto = new AdminVerifiesCoachListDto();
        BeanUtils.copyProperties(coachPo, adminVerifiesCoachListDto);
        adminVerifiesCoachListDtoList.add(adminVerifiesCoachListDto);
      }
      res = BaseResult.success("查询教练审批列表成功");
      res.setData(adminVerifiesCoachListDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询教练审批列表失败");
    }

    return res;
  }

  @Override
  public BaseResult getCoachDetailByCoachId(Integer coachId) {
    //查看审批教练详细信息(审批type=0)
    BaseResult res = null;

    try {
      final CoachPo coachPo = coachMapper.selectByPrimaryKey(coachId);
      final VerifiesPo verifiesPo =verifiesMapper.selectCoachByApplicantId(coachId);
      AdminVerifiesCoachDetailDto adminVerifiesCoachDetailDto = new AdminVerifiesCoachDetailDto();
      BeanUtils.copyProperties(coachPo, adminVerifiesCoachDetailDto);
      BeanUtils.copyProperties(verifiesPo, adminVerifiesCoachDetailDto);

      res = BaseResult.success("查看审批教练详细信息(审批type=0)成功");
      res.setData(adminVerifiesCoachDetailDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查看审批教练详细信息(审批type=0)失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult verifiesByApplicantIdAndType(AdminCreateVerifiesDto adminCreateVerifiesDto) {
    //审批教练（type=0）
    BaseResult res = null;

    try {
      if (!adminCreateVerifiesDto.getType().equals(0)) {
        return BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "审批类型错误！");
      }

      VerifiesPo verifiesPo = new VerifiesPo();
      BeanUtils.copyProperties(adminCreateVerifiesDto, verifiesPo);
      verifiesPo.setTime(new Date());
      verifiesMapper.insert(verifiesPo);

      if(adminCreateVerifiesDto.getResult().equals(1)){//result=1代表审批通过
        coachMapper.updateStatus1(adminCreateVerifiesDto.getApplicantId());
        res = BaseResult.success("教练审批通过");
      }else{
        coachMapper.updateStatus2(adminCreateVerifiesDto.getApplicantId());
        res = BaseResult.success("教练审批未通过");
      }

    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("审批教练（type=0）失败");
    }

    return res;
  }
}
