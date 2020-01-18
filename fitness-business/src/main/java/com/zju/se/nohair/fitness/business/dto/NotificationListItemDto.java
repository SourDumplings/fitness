package com.zju.se.nohair.fitness.business.dto;

import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 商家查看通知列表的 Dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/18 21:41
 */
@Component
@Data
public class NotificationListItemDto {

  private Integer fromId;

  private Integer toId;

  private Date time;

  private String name;

  private Integer type;

  private Integer status;
}
