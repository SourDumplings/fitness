package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.VerifiesPo;

public interface VerifiesMapper {

  int insert(VerifiesPo record);

  int insertSelective(VerifiesPo record);

  VerifiesPo selectBusinessByApplicantId(Integer applicantId);

  VerifiesPo selectCoachByApplicantId(Integer coachId);//查看审批教练详细信息(审批type=0)
}