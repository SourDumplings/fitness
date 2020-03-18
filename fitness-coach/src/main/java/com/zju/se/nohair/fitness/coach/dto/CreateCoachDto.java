package com.zju.se.nohair.fitness.coach.dto;

import com.zju.se.nohair.fitness.commons.utils.RegexpUtils;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

/**
 * 教练注册Dto
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-17
 */
@Component
@Data
public class CreateCoachDto {

  @Length(min = 1, max = 20, message = "用户名的长度必须介于 1 - 20 位之间")
  private String username;

  @Length(min = 6, max = 20, message = "密码的长度必须介于 6 - 20 位之间")
  private String password;

  private Integer gender;

  @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
  private String phone;


  @Length(min = 2, max = 20, message = "姓名的长度必须介于 2 - 20 位之间")
  private String name;

  @Pattern(regexp = RegexpUtils.ID_NUMBER, message = "身份证号格式不正确")
  private String idNumber;
}
