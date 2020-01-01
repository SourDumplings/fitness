package com.zju.se.nohair.fitness.customer.dao.mapper;

import com.zju.se.nohair.fitness.customer.dto.PublicCourseItemOfListDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

public interface PublicCourseInCustomerMapper {

  List<PublicCourseItemOfListDto> getListForCustomerWithGymInfo();

  List<PublicCourseItemOfListDto> getListForCustomerExceptCommented(Integer customerId);
}