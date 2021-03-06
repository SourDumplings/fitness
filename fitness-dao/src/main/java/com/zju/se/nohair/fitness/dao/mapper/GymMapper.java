package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.GymPo;
import java.util.List;

public interface GymMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(GymPo record);

  void insertReturnId(GymPo gymPo);

  int insertSelective(GymPo record);

  GymPo selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(GymPo record);

  int updateByPrimaryKey(GymPo record);

  List<String> getGymNameListHasPublicCourse();

  List<String> getGymNameListHasPrivateCourse();

  List<GymPo> getNearGymList();

  List<GymPo> selectByCourseId(Integer courseId);

  List<GymPo> selectAllGyms();

  GymPo selectByBusinessId(Integer businessId);
}