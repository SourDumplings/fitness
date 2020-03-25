package com.zju.se.nohair.fitness.web.admin.dto;

import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 管理员创建审批教练、商家
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-25
 */
@Component
@Data
public class AdminCreateVerifiesDto {

  private Integer applicantId;

  private Integer adminId;

  private Integer result;

  private String content;

  private Integer type;

}
