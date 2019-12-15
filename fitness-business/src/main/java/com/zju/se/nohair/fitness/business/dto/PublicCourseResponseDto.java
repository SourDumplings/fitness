package com.zju.se.nohair.fitness.business.dto;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 商家查看教练对于团课的响应的 dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/15 20:53
 */
@Component
@Data
public class PublicCourseResponseDto {

  private Integer coachId;

  private String coachName;

  private String coachProfilePic;

  private BigDecimal price;

  private Integer status;

}
