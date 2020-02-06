package com.zju.se.nohair.fitness.coach.dto;

import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 教练查看自己发布的私教课列表 dto.
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-01-15
 */
@Component
@Data
public class PrivateCourseListItemDto {

  private Integer id;

  private String name;

  private Integer coachId;

  private Date courseDate;

  private Integer timeSlotId;

  private Integer status;
}
