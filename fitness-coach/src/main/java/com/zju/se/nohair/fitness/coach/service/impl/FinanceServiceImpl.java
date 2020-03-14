package com.zju.se.nohair.fitness.coach.service.impl;

import com.zju.se.nohair.fitness.coach.service.FinanceService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.mapper.ReceiveRecordMapper;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
      logger.error(e.getMessage());
      res = BaseResult.fail("查看教练钱包余额失败");
    }

    return res;
  }
}
