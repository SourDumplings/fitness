package com.zju.se.nohair.fitness.business.dto;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 商家响应私教课的 dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/17 14:31
 */
@Component
@Data
public class ResponseToPrivateCourseDto {
  private Integer courseId;

  private Integer businessId;

  private BigDecimal price;
}
