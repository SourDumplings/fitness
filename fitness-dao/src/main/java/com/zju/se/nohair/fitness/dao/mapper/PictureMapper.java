package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.PicturePo;

public interface PictureMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(PicturePo record);

  int insertSelective(PicturePo record);

  PicturePo selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(PicturePo record);

  int updateByPrimaryKey(PicturePo record);
}