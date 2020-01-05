package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.ResponsesPublicPo;
import com.zju.se.nohair.fitness.dao.po.ResponsesPublicPoKey;
import java.util.List;

public interface ResponsesPublicMapper {

  int deleteByPrimaryKey(ResponsesPublicPoKey key);

  int insert(ResponsesPublicPo record);

  int insertSelective(ResponsesPublicPo record);

  ResponsesPublicPo selectByPrimaryKey(ResponsesPublicPoKey key);

  List<ResponsesPublicPo> selectByCourseId(Integer courseId);

  int updateByPrimaryKeySelective(ResponsesPublicPo record);

  int updateByPrimaryKey(ResponsesPublicPo record);

  List<ResponsesPublicPo> selectByCoachId(Integer coachId);//查看教练响应的团课列表
}