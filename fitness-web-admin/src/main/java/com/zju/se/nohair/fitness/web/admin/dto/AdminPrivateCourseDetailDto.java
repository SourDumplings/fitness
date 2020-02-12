package com.zju.se.nohair.fitness.web.admin.dto;

import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * 后台模块的课程子模块下的私教课详情 dto
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/2/11 22:25
 */
@Component
public class AdminPrivateCourseDetailDto {

  private String courseName;

  private Integer courseId;

  private Integer coachId;

  private String coachName;

  private Integer businessId;

  private String businessName;

  private Integer customerId;

  private String customerName;

  private Double coursePrice;

  private String courseTime;

  private String picUrl;

  private String courseContent;

  public Double getCoursePrice() {
    return coursePrice;
  }

  public void setCoursePrice(Double coursePrice) {
    this.coursePrice = coursePrice;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public Integer getCourseId() {
    return courseId;
  }

  public void setCourseId(Integer courseId) {
    this.courseId = courseId;
  }

  public Integer getCoachId() {
    return coachId;
  }

  public void setCoachId(Integer coachId) {
    this.coachId = coachId;
  }

  public String getCoachName() {
    return coachName;
  }

  public void setCoachName(String coachName) {
    this.coachName = coachName;
  }

  public Integer getBusinessId() {
    return businessId;
  }

  public void setBusinessId(Integer businessId) {
    this.businessId = businessId;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCourseTime() {
    return courseTime;
  }

  public void setCourseTime(String courseTime) {
    this.courseTime = courseTime;
  }

  public String getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

  public String getCourseContent() {
    return courseContent;
  }

  public void setCourseContent(String courseContent) {
    this.courseContent = courseContent;
  }
}
