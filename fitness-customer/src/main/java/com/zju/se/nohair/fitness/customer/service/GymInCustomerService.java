package com.zju.se.nohair.fitness.customer.service;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/23 16:11
 */
public interface GymInCustomerService {

  BaseResult getGymDetail(Integer gymId);

  BaseResult getNearGymList();
}
