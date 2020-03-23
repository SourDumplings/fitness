package com.zju.se.nohair.fitness.customer.dto;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/3/23 11:04
 */
@Component
public class RechargeDto {

  private Integer customerId;

  private BigDecimal amount;

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
