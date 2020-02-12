package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.PublicCoursePo;
import java.util.List;

public interface PublicCourseMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(PublicCoursePo record);

  int insertSelective(PublicCoursePo record);

  PublicCoursePo selectByPrimaryKey(Integer id);

  List<PublicCoursePo> selectByBusinessId(Integer businessId);

  int updateByPrimaryKeySelective(PublicCoursePo record);

  int updateByPrimaryKey(PublicCoursePo record);

  List<PublicCoursePo> selectForResponsing();

  List<PublicCoursePo> selectByCoachId(Integer coachId);

  List<PublicCoursePo> getListForCustomer();

  List<PublicCoursePo> selectFinishedPublicCoursesByCoachId(Integer coachId);

  List<PublicCoursePo> selectRequiredPublicCoursesByCoachId(Integer coachId);

  int finishByPrimaryKey(Integer courseId);

  List<PublicCoursePo> selectAll();
}