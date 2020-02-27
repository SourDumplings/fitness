package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.NotifiesPo;
import com.zju.se.nohair.fitness.dao.po.NotifiesPoKey;
import java.util.List;

public interface NotifiesMapper {

  int deleteByPrimaryKey(NotifiesPoKey key);

  int insert(NotifiesPo record);

  int insertSelective(NotifiesPo record);

  NotifiesPo selectByPrimaryKey(NotifiesPoKey key);

  List<NotifiesPo> selectAllByFromBusinessId(Integer fromId);

  List<NotifiesPo> selectAllByToBusinessId(Integer toId);

  int updateByPrimaryKeySelective(NotifiesPo record);

  int updateByPrimaryKey(NotifiesPo record);

  List<NotifiesPo> selectAllByToCoachId(Integer coachId);

  List<NotifiesPo> selectAllByFromCoachId(Integer coachId);


}