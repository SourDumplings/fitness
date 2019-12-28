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
  List<ReceiveRecordPo> selectByBusinessId(Integer businessId);

  int updateByPrimaryKeySelective(ReceiveRecordPo record);

  int updateByPrimaryKey(ReceiveRecordPo record);
}