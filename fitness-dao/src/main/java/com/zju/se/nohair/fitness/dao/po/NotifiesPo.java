package com.zju.se.nohair.fitness.dao.po;

public class NotifiesPo extends NotifiesPoKey {

  private String name;

  private String content;

  private Integer status;

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

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}