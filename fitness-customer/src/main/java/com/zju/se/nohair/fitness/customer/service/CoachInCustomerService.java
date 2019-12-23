package com.zju.se.nohair.fitness.customer.service;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * 教练的service 接口
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/23 14:19
 */
public interface CoachInCustomerService {

  BaseResult getCoachDetail(Integer coachId);
}
