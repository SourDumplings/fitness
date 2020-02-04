package com.zju.se.nohair.fitness.business.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 商家查看通知详情的 Dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/18 21:43
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
}
