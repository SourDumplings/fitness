package com.zju.se.nohair.fitness.dao.po;

import java.util.Date;

public class PicturePo {

  private Integer id;

  private Integer picGroupId;

  private String picLink;

  private String filePath;

  private Date createdTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getPicGroupId() {
    return picGroupId;
  }

  public void setPicGroupId(Integer picGroupId) {
    this.picGroupId = picGroupId;
  }

  public String getPicLink() {
    return picLink;
  }

  public void setPicLink(String picLink) {
    this.picLink = picLink == null ? null : picLink.trim();
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath == null ? null : filePath.trim();
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }
}