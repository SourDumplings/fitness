package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.mapper.VerifiesMapper;
import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import com.zju.se.nohair.fitness.dao.po.PicturePo;
import com.zju.se.nohair.fitness.dao.po.VerifiesPo;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCreateVerifiesDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminVerifiesBusinessDetailDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminVerifiesBusinessListDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminVerifiesBusinessService;
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
 * 后台商家审核接口实现类
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-22
 */
@Transactional(readOnly = true)
@Service
public class AdminVerifiesBusinessServiceImpl implements AdminVerifiesBusinessService {

  private static Logger logger = LoggerFactory.getLogger(AdminBusinessUserServiceImpl.class);

  private BusinessMapper businessMapper;

  private PictureMapper pictureMapper;

  private CoachMapper coachMapper;

  private VerifiesMapper verifiesMapper;

  @Autowired
  public void setVerifiesMapper(VerifiesMapper verifiesMapper) { this.verifiesMapper = verifiesMapper; }

  @Autowired
  public void setCoachMapper(CoachMapper coachMapper) { this.coachMapper = coachMapper; }

  @Autowired
  public void setBusinessMapper(BusinessMapper businessMapper) { this.businessMapper = businessMapper; }

  @Autowired
  public void setPictureMapper(PictureMapper pictureMapper) {
    this.pictureMapper = pictureMapper;
  }

  @Override
  public BaseResult listBusinessAll() {
    //商家审批列表
    BaseResult res = null;

    try {
      final List<BusinessPo> businessPoList = businessMapper.selectAll();
      List<AdminVerifiesBusinessListDto> adminVerifiesBusinessListDtoList = new ArrayList<>();
      for (BusinessPo businessPo : businessPoList) {
        AdminVerifiesBusinessListDto adminVerifiesBusinessListDto = new AdminVerifiesBusinessListDto();
        BeanUtils.copyProperties(businessPo, adminVerifiesBusinessListDto);
        adminVerifiesBusinessListDtoList.add(adminVerifiesBusinessListDto);
      }
      res = BaseResult.success("查询商家审批列表成功");
      res.setData(adminVerifiesBusinessListDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询商家审批列表失败");
    }

    return res;
  }

  @Override
  public BaseResult getBusinessDetailByBusinessId(Integer businessId) {
    //查看审批商家详细信息
    BaseResult res = null;

    try {
      final BusinessPo businessPo = businessMapper.selectByPrimaryKey(businessId);
      final VerifiesPo verifiesPo =verifiesMapper.selectBusinessByApplicantId(businessId);
      AdminVerifiesBusinessDetailDto adminVerifiesBusinessDetailDto = new AdminVerifiesBusinessDetailDto();
      BeanUtils.copyProperties(businessPo, adminVerifiesBusinessDetailDto);
      BeanUtils.copyProperties(verifiesPo, adminVerifiesBusinessDetailDto);
      if (businessPo.getPicId() != null) {
        final PicturePo picturePo = pictureMapper.selectByPrimaryKey(businessPo.getCertificationPicId());
        adminVerifiesBusinessDetailDto.setCertificationPicId(picturePo.getPicLink());
      } else {
        adminVerifiesBusinessDetailDto.setCertificationPicId(null);
      }


      res = BaseResult.success("查看审批商家详细信息成功");
      res.setData(adminVerifiesBusinessDetailDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查看审批商家详细信息失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult verifiesByApplicantIdAndType(AdminCreateVerifiesDto adminCreateVerifiesDto) {
    //审批商家（type=1）
    BaseResult res = null;

    try {
      if (!adminCreateVerifiesDto.getType().equals(1)) {
        return BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "审批类型错误！");
      }

      VerifiesPo verifiesPo = new VerifiesPo();
      BeanUtils.copyProperties(adminCreateVerifiesDto, verifiesPo);
      verifiesPo.setTime(new Date());
      verifiesMapper.insert(verifiesPo);

      if(adminCreateVerifiesDto.getResult().equals(1)){//result=1代表审批通过
        businessMapper.updateStatus1(adminCreateVerifiesDto.getApplicantId());
        res = BaseResult.success("商家审批通过");
      }else{
        businessMapper.updateStatus2(adminCreateVerifiesDto.getApplicantId());
        res = BaseResult.success("商家审批未通过");
      }

    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("审批商家（type=1）失败");
    }

    return res;
  }
}
