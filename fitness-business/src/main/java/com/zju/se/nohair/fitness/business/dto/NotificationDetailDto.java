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
public class NotificationDetailDto extends NotificationListItemDto
{

  private String content;
}
