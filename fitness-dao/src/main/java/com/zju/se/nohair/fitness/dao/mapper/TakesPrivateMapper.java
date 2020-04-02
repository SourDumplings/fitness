package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.TakesPrivatePo;
import com.zju.se.nohair.fitness.dao.po.TakesPrivatePoKey;
import java.util.List;

public interface TakesPrivateMapper {

  int deleteByPrimaryKey(TakesPrivatePoKey key);

  int insert(TakesPrivatePo record);

  int insertSelective(TakesPrivatePo record);

  TakesPrivatePo selectByPrimaryKey(TakesPrivatePoKey key);

  List<TakesPrivatePo> selectByCustomerId(Integer customerId);

  int updateByPrimaryKeySelective(TakesPrivatePo record);

  int updateByPrimaryKey(TakesPrivatePo record);

  int selectRecordExistOrNot(TakesPrivatePo takesPrivatePo);

  int deleteByPo(TakesPrivatePo takesPrivatePo);

  List<TakesPrivatePo> selectByCourseId(Integer courseId);

  List<TakesPrivatePo> selectPrivateCustomerByCourseId(Integer courseId);

  TakesPrivatePoKey selectByCourseIdAndTimeSlotId(Integer courseId, Integer timeSlotId);
}