package com.zju.se.nohair.fitness.web.admin.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 后台用户管理用户的收款记录列表项.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/1 21:12
 */
@Component
@Data
public class AdminReceiveRecordListItemDto {

  private Integer fromId;

  private String fromUserTypeName;

  private BigDecimal amount;

  private Date createdTime;
}
