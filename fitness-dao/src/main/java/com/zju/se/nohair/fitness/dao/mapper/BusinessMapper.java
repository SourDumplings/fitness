package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import java.util.List;

public interface BusinessMapper
{

  int deleteByPrimaryKey(Integer id);

  int deleteMultiByPrimaryKeys(List<Integer> ids);

  int insert(BusinessPo record);

  void insertReturnId(BusinessPo record);

  int insertSelective(BusinessPo record);

  BusinessPo selectByPrimaryKey(Integer id);

  BusinessPo selectByUsername(String username);

  List<BusinessPo> selectAll();

  int updateByPrimaryKeySelective(BusinessPo record);

  int updateByPrimaryKey(BusinessPo record);

  int updateStatus1(Integer applicantId);//商家审批通过

  int updateStatus2(Integer applicantId);//商家审批未通过
}