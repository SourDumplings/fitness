package com.zju.se.nohair.fitness.customer.service;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.customer.dto.SelectPublicCourseDto;

/**
 * 选团课 service 接口.
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/15 18:06
 */

public interface PublicCourseInCustomerService {

   BaseResult selectPublicCourse(Integer courseId, Integer customerId) ;

   BaseResult getPublicCourseList();

  BaseResult getPublicCourseDetail(Integer courseId, Integer customerId);

  BaseResult getGymNameList();

  BaseResult checkCustomerChosenPublicOrNot(Integer courseId, Integer customerId);

  BaseResult cancelPublicCourse(Integer courseId, Integer customerId);

  BaseResult getPublicCourseListForCustomerExceptCommented(Integer customerId);
}
