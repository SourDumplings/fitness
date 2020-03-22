package com.zju.se.nohair.fitness.web.admin.service;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * 后台商家审核接口
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-22
 */
public interface AdminVerifiesBusinessService {

  BaseResult listBusinessAll();//商家审批列表

  BaseResult getBusinessDetailByBusinessId(Integer businessId);//查看审批商家详细信息
}
