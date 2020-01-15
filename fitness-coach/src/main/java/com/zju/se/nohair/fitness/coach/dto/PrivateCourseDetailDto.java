package com.zju.se.nohair.fitness.coach.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 查看发布的私教课详情dto
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-01-15
 */
@Component
@Data
public class PrivateCourseDetailDto {

  private String name;

  private String content;

  private Integer coachId;

  private Integer businessId;

  private BigDecimal price;

  private BigDecimal gymPrice;

  private Integer picId;

  private Date courseDate;

  private Integer timeSlotId;

  private Integer status;

  private Date createdTime;
}
