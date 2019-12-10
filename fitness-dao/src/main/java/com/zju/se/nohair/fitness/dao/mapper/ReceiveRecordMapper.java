package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.ReceiveRecordPo;

public interface ReceiveRecordMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(ReceiveRecordPo record);

  int insertSelective(ReceiveRecordPo record);

  ReceiveRecordPo selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(ReceiveRecordPo record);

  int updateByPrimaryKey(ReceiveRecordPo record);
}