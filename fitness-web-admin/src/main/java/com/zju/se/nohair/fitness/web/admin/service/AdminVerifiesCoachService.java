package com.zju.se.nohair.fitness.web.admin.service;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-22
 */
public interface AdminVerifiesCoachService {

  BaseResult listCoachAll();//教练审批列表

  BaseResult getCoachDetailByCoachId(Integer coachId);//查看审批教练详细信息(审批type=0)
}
