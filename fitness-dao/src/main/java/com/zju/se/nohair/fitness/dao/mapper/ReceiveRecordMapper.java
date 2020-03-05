package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.ReceiveRecordPo;
import java.util.List;

public interface ReceiveRecordMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(ReceiveRecordPo record);

  int insertSelective(ReceiveRecordPo record);

  ReceiveRecordPo selectByPrimaryKey(Integer id);

  /**
   * 选出和某个商家有关的收款记录.
   *
   * @param businessId
   * @return
   */
  List<ReceiveRecordPo> selectAllByBusinessId(Integer businessId);

  /**
   * 选出该教练所有的收款记录.
   *
   * @param coachId
   * @return
   */
  List<ReceiveRecordPo> selectAllCoachReceiveRecordsByCoachId(Integer coachId);


  /**
   * 选出该顾客所有的消费记录.
   *
   * @param customerId
   * @return
   */
  List<ReceiveRecordPo> selectAllCustomerExpensesByCustomerId(Integer customerId);


  /**
   * 选出该商家所有的收入记录.
   *
   * @param businessId
   * @return
   */
  List<ReceiveRecordPo> selectAllBusinessIncomeRecordsByBusinessId(Integer businessId);

  int updateByPrimaryKeySelective(ReceiveRecordPo record);

  int updateByPrimaryKey(ReceiveRecordPo record);
}