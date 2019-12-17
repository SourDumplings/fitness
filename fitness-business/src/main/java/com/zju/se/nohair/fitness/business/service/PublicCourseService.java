package com.zju.se.nohair.fitness.business.service;

import com.zju.se.nohair.fitness.business.dto.CreatePublicCourseDto;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * 团课 service 接口.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/11 8:36
 */
public interface PublicCourseService {

  BaseResult createPublicCourse(CreatePublicCourseDto createPublicCourseDto);

  BaseResult deletePublicCourseByCourseId(Integer courseId);

  BaseResult listPublicCoursesByBusinessId(Integer businessId);

  BaseResult getPublicCourseDetailByCourseId(Integer courseId);

  BaseResult listPublicCourseResponsesByCourseId(Integer courseId);

  BaseResult acceptResponse(Integer courseId, Integer coachId);
}