package com.zju.se.nohair.fitness.coach.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 修改教练详细信息Dto
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-24
 */
@Component
@Data
public class ChangeCoachDetailDto {

  private Integer id;

  private String adeptness;//擅长

  private Integer gender;

  private String phone;

  private Date birthday;

  private String ps;//个人简介

  private Integer height;

  private Integer weight;

}
