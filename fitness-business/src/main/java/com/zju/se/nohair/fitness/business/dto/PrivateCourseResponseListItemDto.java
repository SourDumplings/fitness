package com.zju.se.nohair.fitness.business.dto;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 商家查看自己对于私教课的响应的 dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/17 14:02
 */
@Component
@Data
public class PrivateCourseResponseListItemDto {

  private Integer courseId;

  private String courseName;

  private String courseDate;

  private BigDecimal coursePrice;

  private BigDecimal gymPrice;

  private Integer status;
}
