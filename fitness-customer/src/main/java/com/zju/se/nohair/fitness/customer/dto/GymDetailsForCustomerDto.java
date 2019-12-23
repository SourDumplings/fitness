package com.zju.se.nohair.fitness.customer.dto;

import com.zju.se.nohair.fitness.dao.po.RatesPo;
import com.zju.se.nohair.fitness.dao.po.VipCardPo;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 健身房的详细信息 包括评分和评论详情 会员卡信息等
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/23 16:15
 */
@Component
public class GymDetailsForCustomerDto {

  private Integer id;

  private String name;

  private String address;

  private String content;

  private Integer bussinessId;

  private Integer picGroupId;

  private double avgRating;

  private List<RatesPo> ratesList;

  private List<VipCardPo> vipCardList;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getBussinessId() {
    return bussinessId;
  }

  public void setBussinessId(Integer bussinessId) {
    this.bussinessId = bussinessId;
  }

  public Integer getPicGroupId() {
    return picGroupId;
  }

  public void setPicGroupId(Integer picGroupId) {
    this.picGroupId = picGroupId;
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

  public List<VipCardPo> getVipCardList() {
    return vipCardList;
  }

  public void setVipCardList(List<VipCardPo> vipCardList) {
    this.vipCardList = vipCardList;
  }
}
