package com.zju.se.nohair.fitness.business.dto;

import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

/**
 * 商家发布新的会员卡服务的 dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/24 16:45
 */
@Component
@Data
public class CreateVipCardDto {

  private Integer businessId;

  private Integer type;

  @Range(min = 0, max = 1000000)
  private BigDecimal price;
}
