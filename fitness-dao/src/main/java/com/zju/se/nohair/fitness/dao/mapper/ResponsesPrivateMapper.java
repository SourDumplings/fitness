package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.ResponsesPrivatePo;
import com.zju.se.nohair.fitness.dao.po.ResponsesPrivatePoKey;

public interface ResponsesPrivateMapper {

  int deleteByPrimaryKey(ResponsesPrivatePoKey key);

  int insert(ResponsesPrivatePo record);

  int insertSelective(ResponsesPrivatePo record);

  ResponsesPrivatePo selectByPrimaryKey(ResponsesPrivatePoKey key);

  int updateByPrimaryKeySelective(ResponsesPrivatePo record);

  int updateByPrimaryKey(ResponsesPrivatePo record);
}