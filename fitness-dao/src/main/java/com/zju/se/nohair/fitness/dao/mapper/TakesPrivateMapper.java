package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.TakesPrivate;
import com.zju.se.nohair.fitness.dao.po.TakesPrivateKey;

public interface TakesPrivateMapper {

  int deleteByPrimaryKey(TakesPrivateKey key);

  int insert(TakesPrivate record);

  int insertSelective(TakesPrivate record);

  TakesPrivate selectByPrimaryKey(TakesPrivateKey key);

  int updateByPrimaryKeySelective(TakesPrivate record);

  int updateByPrimaryKey(TakesPrivate record);
}