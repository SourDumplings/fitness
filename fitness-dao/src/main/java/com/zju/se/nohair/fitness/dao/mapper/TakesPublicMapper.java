package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.TakesPublicPoKey;
import java.util.List;

public interface TakesPublicMapper {

  int deleteByPrimaryKey(TakesPublicPoKey key);

  int insert(TakesPublicPoKey record);

  int insertSelective(TakesPublicPoKey record);

  int selectRecordExistOrNot(TakesPublicPoKey record);

  int countByCourseId(Integer courseId);

  List<TakesPublicPoKey> selectByCourseId(Integer id);
}