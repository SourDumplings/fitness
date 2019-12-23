package com.zju.se.nohair.fitness.customer.dto;

import com.zju.se.nohair.fitness.dao.po.RatesPo;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 教练的详细信息 包括评分和评论详情
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/23 14:24
 */


@Component
public class CoachDetailsForCustomerDto {

  private Integer id;

  private String username;

  private String adeptness;

  private Integer gender;

  private Integer picId;

  private String phone;

  private Date birthday;

  private String name;

  private String ps;

  private Integer height;

  private Integer weight;

  private double avgRating;

  private List<RatesPo> ratesList;

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

  public String getAdeptness() {
    return adeptness;
  }

  public void setAdeptness(String adeptness) {
    this.adeptness = adeptness;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPs() {
    return ps;
  }

  public void setPs(String ps) {
    this.ps = ps;
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

  public double getAvgRating() {
    return avgRating;
  }

  public void setAvgRating(double avgRating) {
    this.avgRating = avgRating;
  }

  public List<RatesPo> getRatesList() {
    return ratesList;
  }

  public void setRatesList(List<RatesPo> ratesList) {
    this.ratesList = ratesList;
  }
}
