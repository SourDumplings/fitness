package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.RatesPo;
import com.zju.se.nohair.fitness.dao.po.RatesPoKey;
import java.math.BigDecimal;
import java.util.List;

public interface RatesMapper {

  int deleteByPrimaryKey(RatesPoKey key);

  int insert(RatesPo record);

  int insertSelective(RatesPo record);

  RatesPo selectByPrimaryKey(RatesPoKey key);

  int updateByPrimaryKeySelective(RatesPo record);

  int updateByPrimaryKey(RatesPo record);

  List<RatesPo> selectByCoachId(Integer coachId);

  List<RatesPo> selectByGymId(Integer gymId);

  BigDecimal countMeanRatingForBusinessUser(Integer businessId);
}