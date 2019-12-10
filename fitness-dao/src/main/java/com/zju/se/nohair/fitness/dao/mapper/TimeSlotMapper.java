package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.TimeSlotPo;

public interface TimeSlotMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(TimeSlotPo record);

  int insertSelective(TimeSlotPo record);

  TimeSlotPo selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(TimeSlotPo record);

  int updateByPrimaryKey(TimeSlotPo record);
}