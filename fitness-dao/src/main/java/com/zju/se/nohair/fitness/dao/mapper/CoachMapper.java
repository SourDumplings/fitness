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

  String selectByUsername(String username);

  int updateStatus1(Integer applicantId);//教练审批通过

  int updateStatus2(Integer applicantId);//教练审批未通过
}