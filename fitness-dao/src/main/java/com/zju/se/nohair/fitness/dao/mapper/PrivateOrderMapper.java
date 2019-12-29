package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.PrivateOrderPo;

public interface PrivateOrderMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(PrivateOrderPo record);

  int insertSelective(PrivateOrderPo record);

  PrivateOrderPo selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(PrivateOrderPo record);

  int updateByPrimaryKey(PrivateOrderPo record);

  int updateToCancelStatus(PrivateOrderPo privateOrderPo);
}