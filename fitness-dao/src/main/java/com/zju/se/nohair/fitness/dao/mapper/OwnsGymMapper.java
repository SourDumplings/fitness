package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.OwnsGymKey;

public interface OwnsGymMapper {

  int deleteByPrimaryKey(OwnsGymKey key);

  int insert(OwnsGymKey record);

  int insertSelective(OwnsGymKey record);
}