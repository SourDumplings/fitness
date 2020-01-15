package com.zju.se.nohair.fitness.coach.service;

import com.zju.se.nohair.fitness.coach.dto.CreatePrivateCourseDto;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import java.util.Date;

/**
 * 私教课service接口
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-24
 */
public interface PrivateCourseService {

  BaseResult createPrivateCourse(CreatePrivateCourseDto createPrivateCourseDto);

  BaseResult listPrivateCourseResponsesByCourseId(Integer courseId);//通过课程id 查看响应私教课的商家列表


  BaseResult acceptResponse(Integer courseId, Integer businessId);//接受商家对于私教课的响应

  BaseResult deletePrivateCourseByCourseId(Integer courseId);//删除发布的私教课

  BaseResult listPrivateCourses(Integer coachId, Date courseDate);//查看教练发布的私教课列表
}
