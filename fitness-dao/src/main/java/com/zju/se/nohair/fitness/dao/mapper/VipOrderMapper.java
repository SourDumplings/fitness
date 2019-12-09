package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.VipOrder;

public interface VipOrderMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(VipOrder record);

  int insertSelective(VipOrder record);

  VipOrder selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(VipOrder record);

  int updateByPrimaryKey(VipOrder record);
}