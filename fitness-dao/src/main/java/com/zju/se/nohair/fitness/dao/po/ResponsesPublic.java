package com.zju.se.nohair.fitness.dao.po;

import java.math.BigDecimal;

public class ResponsesPublic extends ResponsesPublicKey {

  private BigDecimal price;

  private Integer status;

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}