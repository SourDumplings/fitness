package com.zju.se.nohair.fitness.business.dto;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 商家查看自己收款记录的 dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/28 20:26
 */
@Component
@Data
public class BusinessFinanceRecordListItemDto {

  private Integer otherId;

  private String name;

  private BigDecimal amount;

  private Integer type;

  private String createdTime;

}
