package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.Notifies;
import com.zju.se.nohair.fitness.dao.po.NotifiesKey;

public interface NotifiesMapper {

  int deleteByPrimaryKey(NotifiesKey key);

  int insert(Notifies record);

  int insertSelective(Notifies record);

  Notifies selectByPrimaryKey(NotifiesKey key);

  int updateByPrimaryKeySelective(Notifies record);

  int updateByPrimaryKey(Notifies record);
}