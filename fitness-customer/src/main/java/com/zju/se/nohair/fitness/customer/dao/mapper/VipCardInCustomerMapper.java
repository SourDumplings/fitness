package com.zju.se.nohair.fitness.customer.dao.mapper;

import com.zju.se.nohair.fitness.customer.dto.VipCardWithGymInfoDto;
import com.zju.se.nohair.fitness.dao.po.VipCardPo;
import java.util.List;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/30 14:37
 */
public interface VipCardInCustomerMapper {


  List<VipCardWithGymInfoDto> selectByCustomerId(Integer customerId);
}
