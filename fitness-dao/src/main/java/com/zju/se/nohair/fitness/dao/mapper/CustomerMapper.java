package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.CustomerPo;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(CustomerPo record);

  int insertSelective(CustomerPo record);

  CustomerPo selectByPrimaryKey(Integer id);

  List<CustomerPo> selectAll();

  int updateByPrimaryKeySelective(CustomerPo record);

  int updateByPrimaryKey(CustomerPo record);

  int addBalance(@Param("id")Integer id,@Param("price") BigDecimal price);

  int reduceBalance(@Param("id")Integer id,@Param("price") BigDecimal price);
}