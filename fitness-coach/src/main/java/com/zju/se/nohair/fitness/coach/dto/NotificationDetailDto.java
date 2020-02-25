package com.zju.se.nohair.fitness.coach.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-02-21
 */
@Component
@Data
public class NotificationDetailDto {

  private Integer fromId;

  private Integer toId;

  private String time;

  private String name;

  private String content;

  private Integer type;

  private Integer status;
}
