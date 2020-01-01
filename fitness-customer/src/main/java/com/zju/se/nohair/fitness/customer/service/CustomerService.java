package com.zju.se.nohair.fitness.customer.service;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.customer.dto.CommentCourseDto;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/28 18:12
 */
public interface CustomerService {

  BaseResult getCustomerBalance(Integer customerId);

  BaseResult getCustomerVipCardInfo(Integer customerId);

  BaseResult createCommentForPublicCourse(CommentCourseDto commentCourseDto);

  BaseResult createCommentForPrivateCourse(CommentCourseDto commentCourseDto);
}
