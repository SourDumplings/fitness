package com.zju.se.nohair.fitness.dao.po;

import java.math.BigDecimal;

public class ResponsesPrivatePo extends ResponsesPrivatePoKey {

  private String name;

  private String address;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

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