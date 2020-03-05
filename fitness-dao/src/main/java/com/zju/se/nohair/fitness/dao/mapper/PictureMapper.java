package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.PicturePo;
import java.util.List;

public interface PictureMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(PicturePo record);

  int insertSelective(PicturePo record);

  PicturePo selectByPrimaryKey(Integer id);

  List<PicturePo> selectByPicGroupId(Integer picGroupId);

  int updateByPrimaryKeySelective(PicturePo record);

  int updateByPrimaryKey(PicturePo record);

  void insertReturnId(PicturePo picturePo);

  int getAvailablePicGroupId();
}