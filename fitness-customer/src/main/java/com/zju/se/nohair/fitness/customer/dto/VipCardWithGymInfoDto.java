package com.zju.se.nohair.fitness.customer.dto;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/30 14:25
 */
@Component
public class VipCardWithGymInfoDto {

  private Integer id;

  private Integer customerId;

  private Integer gymId;

  private BigDecimal price;

  private Integer type;

  private Date startTime;

  private Boolean availableOrNot;

  private Date endTime;

  private String gymName;

  private String gymAddress;

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

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Boolean getAvailableOrNot() {
    return availableOrNot;
  }

  public void setAvailableOrNot(Boolean availableOrNot) {
    this.availableOrNot = availableOrNot;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public String getGymName() {
    return gymName;
  }

  public void setGymName(String gymName) {
    this.gymName = gymName;
  }

  public String getGymAddress() {
    return gymAddress;
  }

  public void setGymAddress(String gymAddress) {
    this.gymAddress = gymAddress;
  }
}
