package com.zju.se.nohair.fitness.customer.dto;

import com.zju.se.nohair.fitness.dao.po.PicturePo;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/3/23 9:59
 */
@Component
public class CustomerInfoDto {
  private Integer id;

  private String username;

  private String password;

  private Integer gender;

  private Integer picId;

  private String phone;

  private Date birthday;

  private Integer height;

  private Integer weight;

  private BigDecimal balance;

  private Date createdTime;

  private PicturePo pictureInfo;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public Integer getPicId() {
    return picId;
  }

  public void setPicId(Integer picId) {
    this.picId = picId;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public PicturePo getPictureInfo() {
    return pictureInfo;
  }

  public void setPictureInfo(PicturePo pictureInfo) {
    this.pictureInfo = pictureInfo;
  }
}
