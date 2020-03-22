package com.zju.se.nohair.fitness.coach.dto;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 教练端钱包记录dto
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-21
 */
@Component
@Data
public class CoachFinanceRecordListItemDto {

  private Integer otherId;

  private String name;

  private BigDecimal amount;

  private Integer type;

  private String createdTime;
}
