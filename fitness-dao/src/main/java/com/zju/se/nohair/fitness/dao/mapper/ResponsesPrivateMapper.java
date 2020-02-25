package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.ResponsesPrivatePo;
import com.zju.se.nohair.fitness.dao.po.ResponsesPrivatePoKey;
import com.zju.se.nohair.fitness.dao.po.ResponsesPublicPo;
import java.util.List;

public interface ResponsesPrivateMapper {

  int deleteByPrimaryKey(ResponsesPrivatePoKey key);

  int insert(ResponsesPrivatePo record);

  int insertSelective(ResponsesPrivatePo record);

  ResponsesPrivatePo selectByPrimaryKey(ResponsesPrivatePoKey key);

  List<ResponsesPrivatePo> selectByBusinessId(Integer businessId);

  List<ResponsesPrivatePo> selectByCourseId(Integer courseId);

  int updateByPrimaryKey(ResponsesPrivatePo record);

  List<ResponsesPrivatePo> selectBusinessByCourseId(Integer courseId);


}