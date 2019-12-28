package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.OwnsGymPoKey;

public interface OwnsGymMapper {

  OwnsGymPoKey selectByBusinessId(Integer businessId);

  int deleteByPrimaryKey(OwnsGymPoKey key);

  int insert(OwnsGymPoKey record);

  int insertSelective(OwnsGymPoKey record);
}