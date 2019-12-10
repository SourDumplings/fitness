package com.zju.se.nohair.fitness.dao.po;

import java.util.Date;

public class NotifiesPoKey {

  private Integer fromId;

  private Integer toId;

  private Date time;

  public Integer getFromId() {
    return fromId;
  }

  public void setFromId(Integer fromId) {
    this.fromId = fromId;
  }

  public Integer getToId() {
    return toId;
  }

  public void setToId(Integer toId) {
    this.toId = toId;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }
}