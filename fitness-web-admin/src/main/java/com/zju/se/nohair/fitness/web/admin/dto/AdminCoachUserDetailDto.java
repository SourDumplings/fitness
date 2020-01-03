package com.zju.se.nohair.fitness.web.admin.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 教练用户的详情.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/1 20:34
 */
@Component
@Data
public class AdminCoachUserDetailDto {

  private Integer id;

  private String username;

  private String adeptness;

  private Integer gender;

//  private Integer picId;

  private String phone;

  private Date birthday;

  private String name;

  private String idNumber;

//  private Integer certificationPicId;

  private String ps;

  private Integer height;

  private Integer weight;

  private BigDecimal balance;

  private Integer status;

  private Date createdTime;

  private List<AdminReceiveRecordListItemDto> financeRecords;
}
