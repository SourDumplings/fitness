package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.TakesPublicKey;

public interface TakesPublicMapper {

  int deleteByPrimaryKey(TakesPublicKey key);

  int insert(TakesPublicKey record);

  int insertSelective(TakesPublicKey record);
}