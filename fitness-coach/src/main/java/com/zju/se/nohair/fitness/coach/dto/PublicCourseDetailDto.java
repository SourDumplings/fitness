package com.zju.se.nohair.fitness.coach.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 团课详情（教练查看团课）
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-20
 */
@Component
@Data
public class PublicCourseDetailDto {

  private String name;

  private String content;

  private Integer coachId;

  private Integer businessId;

  private BigDecimal price;

  private BigDecimal coachPrice;

  private Integer picId;

  private Integer capacity;

  private Date courseDate;

  private Integer timeSlotId;

  private Integer status;

  private Date createdTime;
}
