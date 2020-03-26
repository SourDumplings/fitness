package com.zju.se.nohair.fitness.business.service;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * 商家金融相关服务接口.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/28 20:13
 */
public interface FinanceService {

  BaseResult getBalanceByBusinessId(Integer businessId);

  BaseResult listFinanceRecordsByBusinessId(Integer businessId);

  BaseResult withdrawAll(Integer businessId);
}
