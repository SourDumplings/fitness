package com.zju.se.nohair.fitness.coach.service;

import com.zju.se.nohair.fitness.coach.dto.ResponseToPublicCourseDto;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * 教练端 团课 Service接口
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-24
 */
public interface PublicCourseService {
  BaseResult listPublicCoursesByBusinessId(Integer BusinessId);//通过商家id查看商家已发布的团课

  BaseResult getPublicCourseDetailByCourseId(Integer courseId);//

  BaseResult responseToPublicCourse(ResponseToPublicCourseDto responseToPublicCourseDto);

  //BaseResult listPublicCoursesForResponsing();//查看团课列表，不传 coachId 表示查看待响应的，

  //BaseResult listResponsedPublicCoursesByCoachId(Integer coachId);//查看团课列表，传了coachId 代表查看该教练响应成功的
}
