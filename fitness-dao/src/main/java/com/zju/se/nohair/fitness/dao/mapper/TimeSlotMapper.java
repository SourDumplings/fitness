package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.TimeSlot;

public interface TimeSlotMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(TimeSlot record);

  int insertSelective(TimeSlot record);

  TimeSlot selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(TimeSlot record);

  int updateByPrimaryKey(TimeSlot record);
}