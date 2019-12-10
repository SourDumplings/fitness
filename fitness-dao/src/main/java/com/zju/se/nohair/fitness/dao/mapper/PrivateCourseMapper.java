package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.PrivateCoursePo;

public interface PrivateCourseMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(PrivateCoursePo record);

  int insertSelective(PrivateCoursePo record);

  PrivateCoursePo selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(PrivateCoursePo record);

  int updateByPrimaryKey(PrivateCoursePo record);
}