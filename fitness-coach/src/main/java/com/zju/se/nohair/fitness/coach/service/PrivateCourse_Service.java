package com.zju.se.nohair.fitness.coach.service;

import com.zju.se.nohair.fitness.coach.dto.CreatePrivateCourse_Dto;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * 私教课service接口
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-24
 */
public interface PrivateCourse_Service {

  BaseResult createPrivateCourse(CreatePrivateCourse_Dto createPrivateCourseDto);


}
