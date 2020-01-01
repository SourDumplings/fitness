package com.zju.se.nohair.fitness.coach.service;

import com.zju.se.nohair.fitness.coach.dto.CreatePrivateCourseDto;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * 私教课service接口
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-24
 */
public interface PrivateCourseService {

  BaseResult createPrivateCourse(CreatePrivateCourseDto createPrivateCourseDto);

  /*BaseResult listPrivateCourseResponsesByCourseId(Integer courseId);*///通过课程id 查找响应私教课的商家列表

  BaseResult acceptResponse(Integer courseId, Integer businessId);//接受商家对于私教课的响应
}
