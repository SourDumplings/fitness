package com.zju.se.nohair.fitness.customer.dto;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/3/23 11:24
 */
public class PurchaseVipCardDto {

  private Integer customerId;

  private Integer gymId;

  private Integer type;

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  public Integer getGymId() {
    return gymId;
  }

  public void setGymId(Integer gymId) {
    this.gymId = gymId;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }
}
