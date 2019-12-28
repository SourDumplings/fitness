package com.zju.se.nohair.fitness.coach.dto;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 教练响应团课dto
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-24
 */
@Component
@Data
public class ResponseToPublicCourse_Dto {

  private Integer courseId;

  private Integer coachId;

  private BigDecimal price;
}
