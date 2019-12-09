package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.RotationPic;

public interface RotationPicMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(RotationPic record);

  int insertSelective(RotationPic record);

  RotationPic selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(RotationPic record);

  int updateByPrimaryKey(RotationPic record);
}