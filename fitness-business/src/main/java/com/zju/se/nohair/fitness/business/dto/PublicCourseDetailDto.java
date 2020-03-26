package com.zju.se.nohair.fitness.business.dto;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 团课详情 dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/15 20:24
 */
@Component
@Data
public class PublicCourseDetailDto {

  private String name;

  private String content;

  private Integer coachId;

  private String coachName;

  private String coachProfileLink;

  private Integer businessId;

  private BigDecimal price;

  private BigDecimal coachPrice;

  private Integer picId;

  private Integer capacity;

  private Integer takeNum;

  private String courseDate;

  private Integer timeSlotId;

  private Integer status;

  private String createdTime;
}
