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
public class PublicCourseListItem_Dto {

  private Integer id;

  private String name;

  private Integer businessId;

  private Date courseDate;

  private Integer timeSlotId;

  private Integer status;
}
