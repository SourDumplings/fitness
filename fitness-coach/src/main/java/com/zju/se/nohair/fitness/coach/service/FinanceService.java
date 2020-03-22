package com.zju.se.nohair.fitness.coach.service;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * 教练钱包接口
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-15
 */
public interface FinanceService {

  BaseResult getBalanceByCoachId(Integer coachId);//查看教练钱包余额

  BaseResult listFinanceRecordsByCoachId(Integer coachId);//查询教练端交易记录的列表
}
