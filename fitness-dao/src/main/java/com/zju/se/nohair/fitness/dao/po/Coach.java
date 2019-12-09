package com.zju.se.nohair.fitness.dao.po;

import java.math.BigDecimal;
import java.util.Date;

public class Coach {

  private Integer id;

  private String username;

  private String password;

  private String adeptness;

  private Integer gender;

  private Integer picId;

  private String phone;

  private Date birthday;

  private String name;

  private String idNumber;

  private Integer certificationPicId;

  private String ps;

  private Integer height;

  private Integer weight;

  private BigDecimal balance;

  private Integer status;

  private Date createdTime;

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
    this.username = username == null ? null : username.trim();
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password == null ? null : password.trim();
  }

  public String getAdeptness() {
    return adeptness;
  }

  public void setAdeptness(String adeptness) {
    this.adeptness = adeptness == null ? null : adeptness.trim();
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
    this.phone = phone == null ? null : phone.trim();
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  public String getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(String idNumber) {
    this.idNumber = idNumber == null ? null : idNumber.trim();
  }

  public Integer getCertificationPicId() {
    return certificationPicId;
  }

  public void setCertificationPicId(Integer certificationPicId) {
    this.certificationPicId = certificationPicId;
  }

  public String getPs() {
    return ps;
  }

  public void setPs(String ps) {
    this.ps = ps == null ? null : ps.trim();
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

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }
}