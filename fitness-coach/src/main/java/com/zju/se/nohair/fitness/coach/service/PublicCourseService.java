package com.zju.se.nohair.fitness.coach.service;

import com.zju.se.nohair.fitness.coach.dto.ResponseToPublicCourseDto;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import java.util.Date;

/**
 * 教练端 团课 Service接口
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-24
 */
public interface PublicCourseService {

  BaseResult getPublicCourseDetailByCourseId(Integer courseId);//

  BaseResult responseToPublicCourse(ResponseToPublicCourseDto responseToPublicCourseDto);

  BaseResult listPublicCoursesForResponsing(Date courseDate);//不传 coachId 表示查看待响应的团课列表

  BaseResult listResponsedPublicCourses(Integer coachId, Date courseDate);//传了 coachsId 代表查看该教练响应成功的团课列表


}
