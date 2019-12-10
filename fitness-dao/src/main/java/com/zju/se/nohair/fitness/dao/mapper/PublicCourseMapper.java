package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.PublicCoursePo;

public interface PublicCourseMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(PublicCoursePo record);

  int insertSelective(PublicCoursePo record);

  PublicCoursePo selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(PublicCoursePo record);

  int updateByPrimaryKey(PublicCoursePo record);
}