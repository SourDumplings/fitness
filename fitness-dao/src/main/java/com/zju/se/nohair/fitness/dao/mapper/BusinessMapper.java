package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.BusinessPo;

public interface BusinessMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(BusinessPo record);

  int insertSelective(BusinessPo record);

  BusinessPo selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(BusinessPo record);

  int updateByPrimaryKey(BusinessPo record);
}