package com.zju.se.nohair.fitness.business.dto;

import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 商家用户详情 Dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/17 17:51
 */
@Component
@Data
public class BusinessUserDetailDto {

  private String username;

  private String picPath;

  private String phone;

  private String personName;

  private String idNumber;

  private String certificationPicPath;

  private Date createdTime;
}
