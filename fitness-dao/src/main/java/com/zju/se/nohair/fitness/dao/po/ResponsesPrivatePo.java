package com.zju.se.nohair.fitness.dao.po;

import java.math.BigDecimal;

public class ResponsesPrivatePo extends ResponsesPrivatePoKey {

  private String address;

  private String businessName;

  private BigDecimal price;

  private Integer status;

  public String getBusinessName() { return businessName; }

  public void setBusinessName(String businessName) { this.businessName = businessName; }

  public String getAddress() { return address; }

  public void setAddress(String address) { this.address = address; }

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