package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.ResponsesPublic;
import com.zju.se.nohair.fitness.dao.po.ResponsesPublicKey;

public interface ResponsesPublicMapper {

  int deleteByPrimaryKey(ResponsesPublicKey key);

  int insert(ResponsesPublic record);

  int insertSelective(ResponsesPublic record);

  ResponsesPublic selectByPrimaryKey(ResponsesPublicKey key);

  int updateByPrimaryKeySelective(ResponsesPublic record);

  int updateByPrimaryKey(ResponsesPublic record);
}