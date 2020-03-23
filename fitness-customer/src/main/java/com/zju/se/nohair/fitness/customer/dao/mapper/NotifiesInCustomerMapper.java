package com.zju.se.nohair.fitness.customer.dao.mapper;

import com.zju.se.nohair.fitness.customer.dto.NotifiesDto;
import com.zju.se.nohair.fitness.customer.dto.PrivateCourseItemOfListDto;
import java.util.List;

public interface NotifiesInCustomerMapper {

  List<NotifiesDto> selectAllFromCoachToCustomer(Integer customerId);

  List<NotifiesDto> selectAllFromBusinessToCustomer(Integer customerId);
}