package com.zju.se.nohair.fitness.web.admin.dto;

import org.springframework.stereotype.Component;

/**
 * 后台模块的课程子模块下的团课列表项 dto
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/2/12 13:12
 */
@Component
public class AdminPublicCourseListItemDto {

  private Integer courseId;

  private Integer customerId;

  private String coachName;

  private String gymName;

  private String customerName;

  private String courseName;

  private String businessName;

  private Integer capacity;

  public Integer getCourseId() {
    return courseId;
  }

  public void setCourseId(Integer courseId) {
    this.courseId = courseId;
  }

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  public String getCoachName() {
    return coachName;
  }

  public void setCoachName(String coachName) {
    this.coachName = coachName;
  }

  public String getGymName() {
    return gymName;
  }

  public void setGymName(String gymName) {
    this.gymName = gymName;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }
}
