package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.PrivateOrder;

public interface PrivateOrderMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(PrivateOrder record);

  int insertSelective(PrivateOrder record);

  PrivateOrder selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(PrivateOrder record);

  int updateByPrimaryKey(PrivateOrder record);
}