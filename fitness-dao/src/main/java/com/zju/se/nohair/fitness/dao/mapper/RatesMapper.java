package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.Rates;
import com.zju.se.nohair.fitness.dao.po.RatesKey;

public interface RatesMapper {

  int deleteByPrimaryKey(RatesKey key);

  int insert(Rates record);

  int insertSelective(Rates record);

  Rates selectByPrimaryKey(RatesKey key);

  int updateByPrimaryKeySelective(Rates record);

  int updateByPrimaryKey(Rates record);
}