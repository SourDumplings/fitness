package com.zju.se.nohair.fitness.customer.dto;

import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.dao.po.GymPo;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/3/23 13:33
 */
@Component
public class NotifiesDto {

  private Integer fromId;

  private GymPo gym;

  private CoachPo coach;

  private Integer toId;

  private Date time;

  private String name;

  private String content;

  private Integer type;

  private Integer status;

  public Integer getFromId() {
    return fromId;
  }

  public void setFromId(Integer fromId) {
    this.fromId = fromId;
  }

  public GymPo getGym() {
    return gym;
  }

  public void setGym(GymPo gym) {
    this.gym = gym;
  }

  public CoachPo getCoach() {
    return coach;
  }

  public void setCoach(CoachPo coach) {
    this.coach = coach;
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

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
