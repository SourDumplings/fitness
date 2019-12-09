package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.Picture;

public interface PictureMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(Picture record);

  int insertSelective(Picture record);

  Picture selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(Picture record);

  int updateByPrimaryKey(Picture record);
}