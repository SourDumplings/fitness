package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import java.util.List;

public interface BusinessMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(BusinessPo record);

  void insertReturnId(BusinessPo record);

  int insertSelective(BusinessPo record);

  BusinessPo selectByPrimaryKey(Integer id);

  List<BusinessPo> selectAll();

  int updateByPrimaryKeySelective(BusinessPo record);

  int updateByPrimaryKey(BusinessPo record);
}