package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.PrivateTimeSlotPoKey;
import java.util.List;

public interface PrivateTimeSlotMapper {

  int deleteByPrimaryKey(PrivateTimeSlotPoKey key);

  int insert(PrivateTimeSlotPoKey record);

  int insertSelective(PrivateTimeSlotPoKey record);
}