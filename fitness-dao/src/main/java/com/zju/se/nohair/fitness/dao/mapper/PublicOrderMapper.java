package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.PublicOrderPo;

public interface PublicOrderMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(PublicOrderPo record);

  int insertSelective(PublicOrderPo record);

  PublicOrderPo selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(PublicOrderPo record);

  int updateByPrimaryKey(PublicOrderPo record);
}