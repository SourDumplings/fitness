package com.zju.se.nohair.fitness.web.admin.dto;

import com.zju.se.nohair.fitness.commons.utils.RegexpUtils;
import java.util.Date;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

/**
 * 后台创建顾客用户的 Dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/2/2 19:32
 */
@Component
@Data
public class AdminCreateCustomerUserDto {

  private Integer id;

  @Length(min = 1, max = 20, message = "用户名的长度必须介于 1 - 20 位之间")
  private String username;

  @Length(min = 6, max = 20, message = "密码的长度必须介于 6 - 20 位之间")
  private String password;

  @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
  private String phone;

  @Range(min = 0, max = 1, message = "性别只能是 0（男）或者 1（女）")
  private Integer gender;

  private Date birthday;

  private Integer height;

  private Integer weight;

}
