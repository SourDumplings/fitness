package com.zju.se.nohair.fitness.web.admin.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/3/2 14:29
 */
@Component
@Data
public class AdminBusinessUserDetailDto {

  private String idNumber;

  private String phone;

  private String personName;

  private Integer id;

  private BigDecimal balance;

  private BigDecimal rating;

  private BigDecimal monthlyIncome;

  private BigDecimal totalIncome;

  private String certificationPicLink;

  private List<String> gymPicLinks;

  private int[] genderDistribution;

  private BigDecimal[] weeklyIncomeDistribution;

  private List<AdminBusinessUserReceiveRecordListItemDto> receiveRecords;

}
