package com.zju.se.nohair.fitness.web.admin.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/3/5 11:09
 */
@Component
@Data
public class AdminBusinessUserReceiveRecordListItemDto {

  private Integer fromId;

  private String fromUserType;

  private BigDecimal amount;

  private Date createdTime;
}
