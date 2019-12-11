package com.zju.se.nohair.fitness.business.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

/**
 * 商家发布团课的 dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/11 8:32
 */
@Component
@Data
public class CreatePublicCourseDto {

  @Length(min = 3, max = 20, message = "课程名的长度不符合要求")
  private String name;

  private String content;

  private Integer businessId;

  @Range(min = 0, max = 1000000)
  private BigDecimal price;

  @Range(min = 0, max = 1000000)
  private BigDecimal coachPrice;

  private Integer picId;

  @Range(min = 1, max = 1000)
  private Integer capacity;

  private Date courseDate;

  private Integer timeSlotId;
}
