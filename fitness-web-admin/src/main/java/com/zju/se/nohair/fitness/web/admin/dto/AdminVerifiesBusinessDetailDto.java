package com.zju.se.nohair.fitness.web.admin.dto;

import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 后台审批模块商家详情Dto
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-22
 */
@Component
@Data
public class AdminVerifiesBusinessDetailDto {

  private Integer id;//商家id

  private String personName;//商家姓名

  private Integer status;//审批状态

  private String idNumber;//身份证id

  private Date createdTime;//创建时间

  private String phone;

  private String certificationPicId;

  private Integer result;//审批结果

  private String content;//审批内容


}
