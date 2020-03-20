package com.zju.se.nohair.fitness.coach.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 阅读通知的Dto
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-20
 */
@Component
@Data
public class ReadNotificationDetailDto {

  private Integer fromId;

  private Integer toId;

  //private String time;

  private Integer type;

}
