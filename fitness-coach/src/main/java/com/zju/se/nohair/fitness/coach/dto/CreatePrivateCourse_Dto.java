package com.zju.se.nohair.fitness.coach.dto;

import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * 教练发布私教课的 dto.
 *
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/19
 */
public class CreatePrivateCourse_Dto {

  @Length(min = 3, max = 20, message = "课程名的长度不符合要求")
  private String name;

  private String content;

  private Integer coachId;

  private Integer businessId;

  @Range(min = 0, max = 1000000)
  private BigDecimal price;

  @Range(min = 0, max = 1000000)
  private BigDecimal gymPrice;

  private Integer picId;

  private Date courseDate;

  private Integer timeSlotId;

}
