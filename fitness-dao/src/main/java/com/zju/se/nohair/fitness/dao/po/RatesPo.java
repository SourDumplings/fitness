package com.zju.se.nohair.fitness.dao.po;

public class RatesPo extends RatesPoKey {

  private Integer ratingPoints;

  private String content;

  private Integer picGroupId;

  private Integer type;

  private Integer courseId;

  public Integer getRatingPoints() {
    return ratingPoints;
  }

  public void setRatingPoints(Integer ratingPoints) {
    this.ratingPoints = ratingPoints;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content == null ? null : content.trim();
  }

  public Integer getPicGroupId() {
    return picGroupId;
  }

  public void setPicGroupId(Integer picGroupId) {
    this.picGroupId = picGroupId;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getCourseId() {
    return courseId;
  }

  public void setCourseId(Integer courseId) {
    this.courseId = courseId;
  }
}