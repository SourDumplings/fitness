package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.Coach;

public interface CoachMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(Coach record);

  int insertSelective(Coach record);

  Coach selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(Coach record);

  int updateByPrimaryKey(Coach record);
}