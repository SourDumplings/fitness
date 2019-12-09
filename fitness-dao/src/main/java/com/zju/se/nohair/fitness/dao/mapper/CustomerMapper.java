package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.Customer;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "CustomerMapper")
public interface CustomerMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(Customer record);

  int insertSelective(Customer record);

  Customer selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(Customer record);

  int updateByPrimaryKey(Customer record);

  List<Customer> selectAll();
}