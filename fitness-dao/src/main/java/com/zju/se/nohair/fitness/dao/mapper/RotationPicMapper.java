package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.RotationPicPo;

public interface RotationPicMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(RotationPicPo record);

  int insertSelective(RotationPicPo record);

  RotationPicPo selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(RotationPicPo record);

  int updateByPrimaryKey(RotationPicPo record);
}