package com.zju.se.nohair.fitness.coach.service.impl;

import com.zju.se.nohair.fitness.coach.dto.CoachFinanceRecordListItemDto;
import com.zju.se.nohair.fitness.coach.service.FinanceService;
import com.zju.se.nohair.fitness.commons.constant.ReceiveRecordType;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.commons.utils.DateUtils;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.mapper.ReceiveRecordMapper;
import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.dao.po.CustomerPo;
import com.zju.se.nohair.fitness.dao.po.ReceiveRecordPo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 教练钱包接口实现类
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-15
 */
@Transactional(readOnly = true)
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
  public BaseResult getBalanceByCoachId(Integer coachId) {
    //查看教练钱包余额
    BaseResult res = null;

    try {
      final CoachPo coachPo = coachMapper.selectByPrimaryKey(coachId);

      if (coachPo == null) {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "找不到此教练信息！");
      } else {
        res = BaseResult.success("查看教练钱包余额成功");
        res.setData(coachPo.getBalance());
      }
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("查看教练钱包余额失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult listFinanceRecordsByCoachId(Integer coachId) {
    //查询教练端交易记录的列表
    BaseResult res = null;

    try {
      final List<ReceiveRecordPo> receiveRecordPos = receiveRecordMapper.selectAllByCoachId(coachId);
      final CoachPo coachPo = coachMapper.selectByPrimaryKey(coachId);
      if (receiveRecordPos == null) {
        return BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "错误：找不到教练！");
      }
      BigDecimal balance = new BigDecimal(0);
      BigDecimal coachFee = new BigDecimal(0);
      BigDecimal gymFee = new BigDecimal(0);
      BigDecimal privateCourseFee = new BigDecimal(0);

      List<CoachFinanceRecordListItemDto> coachFinanceRecordListItemDtos = new ArrayList<>();
      for (ReceiveRecordPo receiveRecordPo : receiveRecordPos) {
        CoachFinanceRecordListItemDto coachFinanceRecordListItemDto = new CoachFinanceRecordListItemDto();
        BeanUtils.copyProperties(receiveRecordPo, coachFinanceRecordListItemDto);
        coachFinanceRecordListItemDto.setCreatedTime(DateUtils.date2String(receiveRecordPo.getCreatedTime()));

        if (receiveRecordPo.getType().equals(ReceiveRecordType.COACH_FEE)) {
          // 教练费
          final BusinessPo businessPo = businessMapper.selectByPrimaryKey(receiveRecordPo.getToId());
          coachFinanceRecordListItemDto.setName(businessPo.getPersonName());
          coachFinanceRecordListItemDto.setOtherId(receiveRecordPo.getToId());
           coachFee = coachFee.add(coachFinanceRecordListItemDto.getAmount());

        } else if (receiveRecordPo.getType().equals(ReceiveRecordType.GYM_FEE)) {
          // 场地费
          final BusinessPo businessPo = businessMapper.selectByPrimaryKey(receiveRecordPo.getToId());
          coachFinanceRecordListItemDto.setOtherId(receiveRecordPo.getToId());
          coachFinanceRecordListItemDto.setName(businessPo.getPersonName());
          coachFinanceRecordListItemDto.setAmount(coachFinanceRecordListItemDto.getAmount().negate());//显示金额为负
          gymFee = gymFee.add(coachFinanceRecordListItemDto.getAmount().negate());

        } else if (receiveRecordPo.getType().equals(ReceiveRecordType.PRIVATE_COURSE_FEE)) {
          // 私教课课程费
          final CustomerPo customerPo = customerMapper.selectByPrimaryKey(receiveRecordPo.getFromId());
          coachFinanceRecordListItemDto.setOtherId(receiveRecordPo.getFromId());
          coachFinanceRecordListItemDto.setName(customerPo.getUsername());
          privateCourseFee = privateCourseFee.add(coachFinanceRecordListItemDto.getAmount());

        }

        coachFinanceRecordListItemDtos.add(coachFinanceRecordListItemDto);

      }

      balance = balance.add(coachFee);
      balance = balance.subtract(gymFee);
      balance = balance.add(privateCourseFee);
      //System.out.println(balance);
      coachPo.setBalance(balance);
      coachMapper.updateByPrimaryKey(coachPo);

      res = BaseResult.success("查询商家交易列表成功");
      res.setData(coachFinanceRecordListItemDtos);
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("查询商家交易列表失败");
    }

    return res;
  }
}
