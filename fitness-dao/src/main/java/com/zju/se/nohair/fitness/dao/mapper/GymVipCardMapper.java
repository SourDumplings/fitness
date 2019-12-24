package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.GymVipCardPo;
import com.zju.se.nohair.fitness.dao.po.GymVipCardPoKey;
import java.util.List;

public interface GymVipCardMapper {

  int deleteByPrimaryKey(GymVipCardPoKey key);

  int insert(GymVipCardPo record);

  int insertSelective(GymVipCardPo record);

  GymVipCardPo selectByPrimaryKey(GymVipCardPoKey key);

  List<GymVipCardPo> selectAllByGymId(Integer gymId);

  int updateByPrimaryKeySelective(GymVipCardPo record);

  int updateByPrimaryKey(GymVipCardPo record);
}