package com.zju.se.nohair.fitness.dao.po;

import java.math.BigDecimal;

public class GymVipCardPo extends GymVipCardPoKey {

  private BigDecimal price;

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}