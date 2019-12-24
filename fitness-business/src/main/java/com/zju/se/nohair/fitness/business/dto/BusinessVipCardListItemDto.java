package com.zju.se.nohair.fitness.business.dto;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 商家对于自己发布的会员卡的 Dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/24 16:20
 */
@Component
@Data
public class BusinessVipCardListItemDto {

  private String gymName;

  private Integer type;

  private BigDecimal price;
}
