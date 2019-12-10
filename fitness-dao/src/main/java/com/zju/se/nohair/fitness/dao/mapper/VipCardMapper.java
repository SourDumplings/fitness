package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.VipCardPo;

public interface VipCardMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(VipCardPo record);

  int insertSelective(VipCardPo record);

  VipCardPo selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(VipCardPo record);

  int updateByPrimaryKey(VipCardPo record);
}