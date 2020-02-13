package com.zju.se.nohair.fitness.web.admin.dto;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 后台查看顾客用户详情里已购课程列表的 Dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/2/11 20:21
 */
@Component
@Data
public class AdminCustomerUserCourseBoughtListItemDto {

  private Integer id;

  private String type;

  private String businessUsername;

  private String coachName;

  private String courseTime;

  private BigDecimal price;
}
