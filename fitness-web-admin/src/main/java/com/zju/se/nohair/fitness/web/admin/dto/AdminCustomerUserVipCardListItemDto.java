package com.zju.se.nohair.fitness.web.admin.dto;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 后台查看顾客用户详情里会员卡列表的 Dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/2/11 20:11
 */
@Component
@Data
public class AdminCustomerUserVipCardListItemDto {

  private String businessName;

  private BigDecimal price;

  private String validPeriod;

  private Integer id;
}
