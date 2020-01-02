package com.zju.se.nohair.fitness.business.service.impl;

import com.zju.se.nohair.fitness.business.dto.BusinessFinanceRecordListItemDto;
import com.zju.se.nohair.fitness.business.service.FinanceService;
import com.zju.se.nohair.fitness.commons.constant.ReceiveRecordType;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.mapper.ReceiveRecordMapper;
import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.dao.po.CustomerPo;
import com.zju.se.nohair.fitness.dao.po.ReceiveRecordPo;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商家金融相关服务实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/28 20:17
 */
@Service
public class FinanceServiceImpl implements FinanceService {

  private static Logger logger = LoggerFactory.getLogger(FinanceServiceImpl.class);

  private BusinessMapper businessMapper;

  private CoachMapper coachMapper;

  private CustomerMapper customerMapper;

  private ReceiveRecordMapper receiveRecordMapper;

  @Autowired
  public void setBusinessMapper(BusinessMapper businessMapper) {
    this.businessMapper = businessMapper;
  }

  @Autowired
  public void setCoachMapper(CoachMapper coachMapper) {
    this.coachMapper = coachMapper;
  }

  @Autowired
  public void setCustomerMapper(CustomerMapper customerMapper) {
    this.customerMapper = customerMapper;
  }

  @Autowired
  public void setReceiveRecordMapper(ReceiveRecordMapper receiveRecordMapper) {
    this.receiveRecordMapper = receiveRecordMapper;
  }

  @Override
  public BaseResult getBalanceByBusinessId(Integer businessId) {
    BaseResult res = null;

    try {
      final BusinessPo businessPo = businessMapper.selectByPrimaryKey(businessId);

      if (businessPo == null) {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "错误：无此商家！");
      } else {
        res = BaseResult.success("查询商家钱包余额成功");
        res.setData(businessPo.getBalance());
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询商家钱包余额失败");
    }

    return res;
  }

  @Override
  public BaseResult listFinanceRecordsByBusinessId(Integer businessId) {
    BaseResult res = null;

    try {
      final List<ReceiveRecordPo> receiveRecordPos = receiveRecordMapper
          .selectAllByBusinessId(businessId);

      if (receiveRecordPos == null) {
        return BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "错误：无此商家！");
      }

      List<BusinessFinanceRecordListItemDto> businessFinanceRecordListItemDtos = new ArrayList<>();
      for (ReceiveRecordPo receiveRecordPo : receiveRecordPos) {
        BusinessFinanceRecordListItemDto businessFinanceRecordListItemDto = new BusinessFinanceRecordListItemDto();
        BeanUtils.copyProperties(receiveRecordPo, businessFinanceRecordListItemDto);

        if (receiveRecordPo.getType().equals(ReceiveRecordType.COACH_FEE)) {
          // 教练费
          final CoachPo coachPo = coachMapper.selectByPrimaryKey(receiveRecordPo.getToId());
          businessFinanceRecordListItemDto.setName(coachPo.getName());
          businessFinanceRecordListItemDto.setOtherId(receiveRecordPo.getToId());
          // 商家为付款方，金额为负值
          businessFinanceRecordListItemDto
              .setAmount(businessFinanceRecordListItemDto.getAmount().negate());
        } else if (receiveRecordPo.getType().equals(ReceiveRecordType.GYM_FEE)) {
          // 场地费
          final CoachPo coachPo = coachMapper.selectByPrimaryKey(receiveRecordPo.getFromId());
          businessFinanceRecordListItemDto.setOtherId(receiveRecordPo.getFromId());
          businessFinanceRecordListItemDto.setName(coachPo.getName());
        } else if (receiveRecordPo.getType().equals(ReceiveRecordType.PUBLIC_COURSE_FEE)
            || receiveRecordPo.getType().equals(ReceiveRecordType.VIP_CARD_FEE)) {
          // 团课课程费或者会员卡费
          final CustomerPo customerPo = customerMapper
              .selectByPrimaryKey(receiveRecordPo.getFromId());
          businessFinanceRecordListItemDto.setOtherId(receiveRecordPo.getFromId());
          businessFinanceRecordListItemDto.setName(customerPo.getUsername());
        }

        businessFinanceRecordListItemDtos.add(businessFinanceRecordListItemDto);

      }
      res = BaseResult.success("查询商家交易列表成功");
      res.setData(businessFinanceRecordListItemDtos);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询商家交易列表失败");
    }

    return res;
  }
}
