package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.CustomerPo;
import java.util.List;

public interface CustomerMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(CustomerPo record);

  int insertSelective(CustomerPo record);

  CustomerPo selectByPrimaryKey(Integer id);

  List<CustomerPo> selectAll();

  int updateByPrimaryKeySelective(CustomerPo record);

  int updateByPrimaryKey(CustomerPo record);
}