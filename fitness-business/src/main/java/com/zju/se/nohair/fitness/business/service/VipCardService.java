package com.zju.se.nohair.fitness.business.service;

import com.zju.se.nohair.fitness.business.dto.CreateVipCardDto;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * 商家针对会员卡的服务接口.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/24 16:17
 */
public interface VipCardService {

  BaseResult listVipCardInfo(Integer businessId);

  BaseResult pushNewCardService(CreateVipCardDto createVipCardDto);
}
