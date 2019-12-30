package com.zju.se.nohair.fitness.business.service;

import com.zju.se.nohair.fitness.business.dto.ResponseToPrivateCourseDto;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import java.util.Date;

/**
 * 私教课 service 接口.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/17 13:38
 */
public interface PrivateCourseService {

  BaseResult listResponsesByBusinessId(Integer businessId);

  BaseResult listResponsedPrivateCourses(Integer businessId, Date courseDate);

  BaseResult listPrivateCoursesForResponsing(Date courseDate);

  BaseResult getPrivateCourseDetailByCourseId(Integer courseId);

  BaseResult responseToPrivateCourse(ResponseToPrivateCourseDto responseToPrivateCourseDto);

  BaseResult changeResponsePrice(ResponseToPrivateCourseDto responseToPrivateCourseDto);

  BaseResult deleteResponse(Integer courseId, Integer businessId);
}
