package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.CoachPo;
import java.util.List;

public interface CoachMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(CoachPo record);

  int insertSelective(CoachPo record);

  CoachPo selectByPrimaryKey(Integer id);

  List<CoachPo> selectAll();

  int updateByPrimaryKeySelective(CoachPo record);

  int updateByPrimaryKey(CoachPo record);

  int insertReturnId(CoachPo coachPo);
}