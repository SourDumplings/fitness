package com.zju.se.nohair.fitness.business.dto;

import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 商家查看自己分布的团课列表的列表项 dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/15 12:21
 */
@Component
@Data
public class PublicCourseListItemDto {

  private Integer id;

  private String name;

  private Integer businessId;

  private Date courseDate;

  private Integer timeSlotId;

  private Integer status;

}
