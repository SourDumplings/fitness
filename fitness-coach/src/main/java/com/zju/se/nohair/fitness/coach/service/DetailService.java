package com.zju.se.nohair.fitness.coach.service;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * 教练端-我的 接口
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-17
 */
public interface DetailService {

  BaseResult getCoachDetailByCoachId(Integer coachId);//查看教练的个人信息
}
