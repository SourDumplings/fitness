package com.zju.se.nohair.fitness.web.admin.service;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.web.admin.abstracts.AbstractService;

/**
 * 后台模块的课程子模块下的团课 Service 接口.
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/2/12 11:29
 */
public interface AdminPublicCourseService extends AbstractService {

  BaseResult getDetailByIds(Integer courseId, Integer customerId);
}
