package com.zju.se.nohair.fitness.customer.dto;

import com.zju.se.nohair.fitness.dao.po.TimeSlotPo;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/28 16:08
 */
@Component
public class PrivateCourseDetailsForCustomerDto {

  private Integer id;

  private String name;

  private String content;

  private Integer coachId;

  private Integer businessId;

  private BigDecimal price;

  private Integer picId;

  private Date courseDate;

  private Integer status;

  private CoachDetailsForCustomerDto coachDetail;

  private GymDetailsForCustomerDto gymDetail;

  private List<TimeSlotPo> timeSlots;

  private Boolean chosenOrNot;

  private List<TimeSlotPo> buyTimeSlots;

  public Date getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(Date courseDate) {
    this.courseDate = courseDate;
  }

  public List<TimeSlotPo> getTimeSlots() {
    return timeSlots;
  }

  public void setTimeSlots(List<TimeSlotPo> timeSlots) {
    this.timeSlots = timeSlots;
  }

  public Boolean getChosenOrNot() {
    return chosenOrNot;
  }

  public void setChosenOrNot(Boolean chosenOrNot) {
    this.chosenOrNot = chosenOrNot;
  }

  public List<TimeSlotPo> getBuyTimeSlots() {
    return buyTimeSlots;
  }

  public void setBuyTimeSlots(List<TimeSlotPo> buyTimeSlots) {
    this.buyTimeSlots = buyTimeSlots;
  }

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

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getPicId() {
    return picId;
  }

  public void setPicId(Integer picId) {
    this.picId = picId;
  }


  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
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
