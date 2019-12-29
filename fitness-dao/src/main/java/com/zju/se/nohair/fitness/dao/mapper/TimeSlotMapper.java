package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.dao.po.TimeSlotPo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TimeSlotMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(TimeSlotPo record);

  int insertSelective(TimeSlotPo record);

  TimeSlotPo selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(TimeSlotPo record);

  int updateByPrimaryKey(TimeSlotPo record);

  List<TimeSlotPo> getPrivateCourseBookableTimeSlots(Integer courseId);

  List<TimeSlotPo> getPrivateCustomerChosenTimeSlots(@Param("courseId") Integer courseId, @Param("customerId") Integer customerId);
}