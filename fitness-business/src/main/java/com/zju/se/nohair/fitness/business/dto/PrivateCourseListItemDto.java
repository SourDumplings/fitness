package com.zju.se.nohair.fitness.business.dto;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 商家查看私教课列表的列表项 dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/17 13:56
 */
@Component
@Data
public class PrivateCourseListItemDto {

  private Integer id;

  private String name;

  private Integer coachId;

  private String courseDate;

  private BigDecimal price;

  private Integer status;

}
