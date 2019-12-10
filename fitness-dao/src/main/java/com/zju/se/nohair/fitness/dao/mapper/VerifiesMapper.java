package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.VerifiesPo;

public interface VerifiesMapper {

  int insert(VerifiesPo record);

  int insertSelective(VerifiesPo record);
}