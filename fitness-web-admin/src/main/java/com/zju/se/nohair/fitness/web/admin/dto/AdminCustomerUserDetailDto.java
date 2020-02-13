package com.zju.se.nohair.fitness.web.admin.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 后台查看顾客用户详情的 Dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/2/11 20:04
 */
@Component
@Data
public class AdminCustomerUserDetailDto {

  private Integer id;

  private String username;

  private Integer gender;

  private String picLink;

  private String phone;

  private Date birthday;

  private Integer height;

  private Integer weight;

  private BigDecimal balance;

  private BigDecimal monthlyExpense;

  private BigDecimal totalExpense;

//  private Date createdTime;

  private List<AdminCustomerUserVipCardListItemDto> vipCardList;

  private List<AdminCustomerUserCourseBoughtListItemDto> courseList;

}
