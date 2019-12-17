package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.ResponsesPrivatePo;
import com.zju.se.nohair.fitness.dao.po.ResponsesPrivatePoKey;
import java.util.List;

public interface ResponsesPrivateMapper {

  int deleteByPrimaryKey(ResponsesPrivatePoKey key);

  int insert(ResponsesPrivatePo record);

  int insertSelective(ResponsesPrivatePo record);

  ResponsesPrivatePo selectByPrimaryKey(ResponsesPrivatePoKey key);

  List<ResponsesPrivatePo> selectByBusinessId(Integer businessId);

  int updateByPrimaryKeySelective(ResponsesPrivatePo record);

  int updateByPrimaryKey(ResponsesPrivatePo record);
}