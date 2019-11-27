package com.zju.se.nohair.fitness.web.admin.dto;

import com.zju.se.nohair.fitness.commons.utils.RegexpUtils;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 创建用户时的新用户业务对象.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/27 15:31
 */
@Data
public class CreateUserDto {

  @Length(min = 6, max = 20, message = "用户名的长度必须介于 6 - 20 位之间")
  private String username;

  @Length(min = 6, max = 20, message = "密码的长度必须介于 6 - 20 位之间")
  private String password;

  @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
  private String phone;

  @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
  private String email;
}
