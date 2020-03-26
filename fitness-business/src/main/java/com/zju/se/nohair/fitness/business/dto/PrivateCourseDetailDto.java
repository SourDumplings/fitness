package com.zju.se.nohair.fitness.business.dto;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 私教课详情 dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/17 14:28
 */
@Component
@Data
public class PrivateCourseDetailDto {

  private String name;

  private String content;

  private Integer coachId;

  private String coachName;

  private String coachProfileLink;

  private Integer businessId;

  private BigDecimal price;

  private BigDecimal gymPrice;

  private Integer picId;

  private String courseDate;

  private Integer status;

  private String createdTime;
}
