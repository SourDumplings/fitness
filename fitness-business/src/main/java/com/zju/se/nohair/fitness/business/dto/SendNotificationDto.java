package com.zju.se.nohair.fitness.business.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 商家发送通知的 dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/18 21:38
 */
@Component
@Data
public class SendNotificationDto {

  private Integer fromId;

  private Integer toId;

  private String name;

  private String content;

  private Integer type;
}
