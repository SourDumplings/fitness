package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.Business;

public interface BusinessMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(Business record);

  int insertSelective(Business record);

  Business selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(Business record);

  int updateByPrimaryKey(Business record);
}