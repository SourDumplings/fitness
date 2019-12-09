package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.ReceiveRecord;

public interface ReceiveRecordMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(ReceiveRecord record);

  int insertSelective(ReceiveRecord record);

  ReceiveRecord selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(ReceiveRecord record);

  int updateByPrimaryKey(ReceiveRecord record);
}