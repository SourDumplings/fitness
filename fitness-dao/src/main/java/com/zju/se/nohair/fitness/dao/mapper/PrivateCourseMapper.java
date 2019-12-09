package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.PrivateCourse;

public interface PrivateCourseMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(PrivateCourse record);

  int insertSelective(PrivateCourse record);

  PrivateCourse selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(PrivateCourse record);

  int updateByPrimaryKey(PrivateCourse record);
}