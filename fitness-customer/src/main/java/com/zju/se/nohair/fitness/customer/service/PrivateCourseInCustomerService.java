package com.zju.se.nohair.fitness.customer.service;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.customer.dto.SelectPublicCourseDto;
import io.swagger.models.auth.In;

/**
 * 选团课 service 接口.
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/15 18:06
 */

public interface PrivateCourseInCustomerService {

   BaseResult selectPrivateCourse(Integer courseId, Integer customerId, Integer timeSlotId) ;

   BaseResult getPrivateCourseList();

   BaseResult getPrivateCourseDetail(Integer courseId, Integer customerId);

   BaseResult getGymNameList();

   BaseResult checkCustomerChosenPrivateOrNot(Integer courseId, Integer customerId);

   BaseResult cancelPrivateCourse(Integer courseId, Integer customerId, Integer timeSlotId);

  BaseResult getPrivateCourseListForCustomerExceptCommented(Integer customerId);
}
