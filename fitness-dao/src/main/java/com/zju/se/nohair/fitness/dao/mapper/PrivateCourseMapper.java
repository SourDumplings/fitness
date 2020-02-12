package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.PrivateCoursePo;
import java.util.List;

public interface PrivateCourseMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(PrivateCoursePo record);

  int insertSelective(PrivateCoursePo record);

  PrivateCoursePo selectByPrimaryKey(Integer id);

  List<PrivateCoursePo> selectForResponsing();

  List<PrivateCoursePo> selectByBusinessId(Integer businessId);

  int updateByPrimaryKeySelective(PrivateCoursePo record);

  int updateByPrimaryKey(PrivateCoursePo record);

  List<PrivateCoursePo> selectByCoachId(Integer coachId);

  List<PrivateCoursePo> selectFinishedPrivateCoursesByCoachId(Integer coachId);

  List<PrivateCoursePo> selectRequiredPrivateCoursesByCoachId(Integer coachId);

  int finishByPrimaryKey(Integer courseId);

  PrivateCoursePo selectRequiredByCoachId(Integer coachId);

  List<PrivateCoursePo> selectAll();
}