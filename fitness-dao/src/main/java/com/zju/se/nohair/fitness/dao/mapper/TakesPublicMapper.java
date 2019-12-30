package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.TakesPublicPoKey;

public interface TakesPublicMapper {

  int deleteByPrimaryKey(TakesPublicPoKey key);

  int insert(TakesPublicPoKey record);

  int insertSelective(TakesPublicPoKey record);

  int selectRecordExistOrNot(TakesPublicPoKey record);

  int countByCourseId(Integer courseId);
}