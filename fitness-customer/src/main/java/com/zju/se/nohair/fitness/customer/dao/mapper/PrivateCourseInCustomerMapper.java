package com.zju.se.nohair.fitness.customer.dao.mapper;

import com.zju.se.nohair.fitness.customer.dto.PrivateCourseItemOfListDto;
import com.zju.se.nohair.fitness.customer.dto.PublicCourseItemOfListDto;
import java.util.List;

public interface PrivateCourseInCustomerMapper {

  List<PrivateCourseItemOfListDto> getListForCustomerWithGymInfo();
}