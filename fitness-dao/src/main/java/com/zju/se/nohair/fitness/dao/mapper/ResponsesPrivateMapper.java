package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.ResponsesPrivate;
import com.zju.se.nohair.fitness.dao.po.ResponsesPrivateKey;

public interface ResponsesPrivateMapper {

  int deleteByPrimaryKey(ResponsesPrivateKey key);

  int insert(ResponsesPrivate record);

  int insertSelective(ResponsesPrivate record);

  ResponsesPrivate selectByPrimaryKey(ResponsesPrivateKey key);

  int updateByPrimaryKeySelective(ResponsesPrivate record);

  int updateByPrimaryKey(ResponsesPrivate record);
}