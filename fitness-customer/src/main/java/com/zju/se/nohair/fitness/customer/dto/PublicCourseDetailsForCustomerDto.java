package com.zju.se.nohair.fitness.customer.dto;

import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/28 16:08
 */
@Component
public class PublicCourseDetailsForCustomerDto {
  private Integer id;

  private String name;

  private String content;

  private Integer businessId;

  private Integer coachId;

  private Double price;

  private Integer picId;

  private Integer capacity;

  private Date courseDate;

  private Integer timeSlotId;

  private Integer status;

  private Integer chosenCount;

  private CoachDetailsForCustomerDto coachDetail;

  private GymDetailsForCustomerDto gymDetail;

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
    this.name = name;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getBusinessId() {
    return businessId;
  }

  public void setBusinessId(Integer businessId) {
    this.businessId = businessId;
  }

  public Integer getCoachId() {
    return coachId;
  }

  public void setCoachId(Integer coachId) {
    this.coachId = coachId;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getPicId() {
    return picId;
  }

  public void setPicId(Integer picId) {
    this.picId = picId;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public Date getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(Date courseDate) {
    this.courseDate = courseDate;
  }

  public Integer getTimeSlotId() {
    return timeSlotId;
  }

  public void setTimeSlotId(Integer timeSlotId) {
    this.timeSlotId = timeSlotId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getChosenCount() {
    return chosenCount;
  }

  public void setChosenCount(Integer chosenCount) {
    this.chosenCount = chosenCount;
  }

  public CoachDetailsForCustomerDto getCoachDetail() {
    return coachDetail;
  }

  public void setCoachDetail(CoachDetailsForCustomerDto coachDetail) {
    this.coachDetail = coachDetail;
  }

  public GymDetailsForCustomerDto getGymDetail() {
    return gymDetail;
  }

  public void setGymDetail(GymDetailsForCustomerDto gymDetail) {
    this.gymDetail = gymDetail;
  }
}
