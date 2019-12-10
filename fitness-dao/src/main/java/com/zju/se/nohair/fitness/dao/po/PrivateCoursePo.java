package com.zju.se.nohair.fitness.dao.po;

import java.math.BigDecimal;
import java.util.Date;

public class PrivateCoursePo {

  private Integer id;

  private String name;

  private String content;

  private Integer coachId;

  private Integer businessId;

  private BigDecimal price;

  private BigDecimal gymPrice;

  private Integer picId;

  private Date courseDate;

  private String timeSlots;

  private Integer status;

  private Date createdTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content == null ? null : content.trim();
  }

  public Integer getCoachId() {
    return coachId;
  }

  public void setCoachId(Integer coachId) {
    this.coachId = coachId;
  }

  public Integer getBusinessId() {
    return businessId;
  }

  public void setBusinessId(Integer businessId) {
    this.businessId = businessId;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getGymPrice() {
    return gymPrice;
  }

  public void setGymPrice(BigDecimal gymPrice) {
    this.gymPrice = gymPrice;
  }

  public Integer getPicId() {
    return picId;
  }

  public void setPicId(Integer picId) {
    this.picId = picId;
  }

  public Date getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(Date courseDate) {
    this.courseDate = courseDate;
  }

  public String getTimeSlots() {
    return timeSlots;
  }

  public void setTimeSlots(String timeSlots) {
    this.timeSlots = timeSlots == null ? null : timeSlots.trim();
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