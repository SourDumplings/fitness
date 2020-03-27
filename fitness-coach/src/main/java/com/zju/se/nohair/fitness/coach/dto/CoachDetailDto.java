package com.zju.se.nohair.fitness.coach.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 教练端-我的-详情
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-17
 */
@Component
@Data
public class CoachDetailDto {

  private String username;

  private String password;

  private String adeptness;//擅长

  private Integer gender;

  private String picId;//头像图片

  private String phone;

  private Date birthday;

  private String name;

  private String idNumber;

  private String certificationPicId;

  private String ps;//个人简介

  private Integer height;

  private Integer weight;

  private BigDecimal balance;

  private Integer status;

  private Date createdTime;
}
