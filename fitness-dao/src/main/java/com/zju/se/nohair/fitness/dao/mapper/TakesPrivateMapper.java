package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.TakesPrivatePo;
import com.zju.se.nohair.fitness.dao.po.TakesPrivatePoKey;

public interface TakesPrivateMapper {

  int deleteByPrimaryKey(TakesPrivatePoKey key);

  int insert(TakesPrivatePo record);

  int insertSelective(TakesPrivatePo record);

  TakesPrivatePo selectByPrimaryKey(TakesPrivatePoKey key);

  int updateByPrimaryKeySelective(TakesPrivatePo record);

  int updateByPrimaryKey(TakesPrivatePo record);
}