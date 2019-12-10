package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.VipOrderPo;

public interface VipOrderMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(VipOrderPo record);

  int insertSelective(VipOrderPo record);

  VipOrderPo selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(VipOrderPo record);

  int updateByPrimaryKey(VipOrderPo record);
}