package com.zju.se.nohair.fitness.customer.dto;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/3/27 21:39
 */
@Component
public class RegisterDto {

  private String username;

  private String password;

  private Integer gender;

  private String phone;

  private Date birthday;

  private Integer height;

  private Integer weight;

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
}
