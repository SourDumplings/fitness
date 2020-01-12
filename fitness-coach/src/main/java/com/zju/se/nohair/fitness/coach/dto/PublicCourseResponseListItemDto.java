package com.zju.se.nohair.fitness.coach.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 教练查看自己对于团课的响应 dto.（我响应的团课）
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-01-05
 */
@Component
@Data
public class PublicCourseResponseListItemDto {

  private Integer courseId;

  private String courseName;

  private Date courseDate;

  private BigDecimal coursePrice;

  private BigDecimal coachPrice;

  private Integer status;
}
