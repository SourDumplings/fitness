package com.zju.se.nohair.fitness.coach.dto;

import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-24
 */
@Component
@Data
public class PublicCourseListItemDto {

  private Integer id;

  private String name;

  private Integer businessId;

  private String picId;

  private String gymName;

  private Date courseDate;

  private Integer capacity;

  private Integer timeSlotId;

  private Integer status;
}
