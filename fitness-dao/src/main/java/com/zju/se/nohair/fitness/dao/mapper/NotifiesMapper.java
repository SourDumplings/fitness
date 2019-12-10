package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.NotifiesPo;
import com.zju.se.nohair.fitness.dao.po.NotifiesPoKey;

public interface NotifiesMapper {

  int deleteByPrimaryKey(NotifiesPoKey key);

  int insert(NotifiesPo record);

  int insertSelective(NotifiesPo record);

  NotifiesPo selectByPrimaryKey(NotifiesPoKey key);

  int updateByPrimaryKeySelective(NotifiesPo record);

  int updateByPrimaryKey(NotifiesPo record);
}