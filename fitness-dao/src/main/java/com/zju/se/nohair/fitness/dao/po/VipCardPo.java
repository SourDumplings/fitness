package com.zju.se.nohair.fitness.dao.po;

import java.math.BigDecimal;
import java.util.Date;

public class VipCardPo {

  private Integer id;

  private Integer customerId;

  private Integer gymId;

  private BigDecimal price;

  private Integer type;

  private Date createdTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }
}