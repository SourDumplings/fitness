package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.PublicCourse;

public interface PublicCourseMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(PublicCourse record);

  int insertSelective(PublicCourse record);

  PublicCourse selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(PublicCourse record);

  int updateByPrimaryKey(PublicCourse record);
}