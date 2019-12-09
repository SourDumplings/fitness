package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.PublicOrder;

public interface PublicOrderMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(PublicOrder record);

  int insertSelective(PublicOrder record);

  PublicOrder selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(PublicOrder record);

  int updateByPrimaryKey(PublicOrder record);
}