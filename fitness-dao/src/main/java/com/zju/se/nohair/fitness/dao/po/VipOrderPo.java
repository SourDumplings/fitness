package com.zju.se.nohair.fitness.dao.po;

import java.math.BigDecimal;
import java.util.Date;

public class VipOrderPo {

  private Integer id;

  private Integer gymId;

  private BigDecimal orderPrice;

  private Integer status;

  private Date createdTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getGymId() {
    return gymId;
  }

  public void setGymId(Integer gymId) {
    this.gymId = gymId;
  }

  public BigDecimal getOrderPrice() {
    return orderPrice;
  }

  public void setOrderPrice(BigDecimal orderPrice) {
    this.orderPrice = orderPrice;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }
}