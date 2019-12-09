package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.VipCard;

public interface VipCardMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(VipCard record);

  int insertSelective(VipCard record);

  VipCard selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(VipCard record);

  int updateByPrimaryKey(VipCard record);
}