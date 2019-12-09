package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.Gym;

public interface GymMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(Gym record);

  int insertSelective(Gym record);

  Gym selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(Gym record);

  int updateByPrimaryKey(Gym record);
}