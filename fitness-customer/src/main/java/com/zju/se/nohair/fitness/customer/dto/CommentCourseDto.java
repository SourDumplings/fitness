package com.zju.se.nohair.fitness.customer.dto;

import org.springframework.stereotype.Component;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/1 13:33
 */
@Component
public class CommentCourseDto {

  private Integer courseId;

  private Integer customerId;

  private Integer coachId;

  private Integer coachRating;

  private String coachContent;

  private Integer gymId;

  private Integer gymRating;

  private String gymContent;

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

  public Integer getCoachId() {
    return coachId;
  }

  public void setCoachId(Integer coachId) {
    this.coachId = coachId;
  }

  public Integer getCoachRating() {
    return coachRating;
  }

  public void setCoachRating(Integer coachRating) {
    this.coachRating = coachRating;
  }

  public String getCoachContent() {
    return coachContent;
  }

  public void setCoachContent(String coachContent) {
    this.coachContent = coachContent;
  }

  public Integer getGymId() {
    return gymId;
  }

  public void setGymId(Integer gymId) {
    this.gymId = gymId;
  }

  public Integer getGymRating() {
    return gymRating;
  }

  public void setGymRating(Integer gymRating) {
    this.gymRating = gymRating;
  }

  public String getGymContent() {
    return gymContent;
  }

  public void setGymContent(String gymContent) {
    this.gymContent = gymContent;
  }
}
