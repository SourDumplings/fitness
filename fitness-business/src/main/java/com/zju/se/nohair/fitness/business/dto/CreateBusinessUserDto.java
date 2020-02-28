package com.zju.se.nohair.fitness.business.dto;

import com.zju.se.nohair.fitness.commons.utils.RegexpUtils;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

/**
 * 商家注册需要的详情信息 dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/17 17:47
 */
@Component
@Data
public class CreateBusinessUserDto {

  @Length(min = 1, max = 20, message = "用户名的长度必须介于 1 - 20 位之间")
  private String username;

  @Length(min = 6, max = 20, message = "密码的长度必须介于 6 - 20 位之间")
  private String password;

  @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
  private String phone;

  @Length(min = 2, max = 20, message = "姓名的长度必须介于 2 - 20 位之间")
  private String personName;

  @Pattern(regexp = RegexpUtils.ID_NUMBER, message = "身份证号格式不正确")
  private String idNumber;

  @Length(min = 1, max = 30, message = "健身房名的长度必须介于 1 - 30 位之间")
  private String gymName;

  @Length(min = 1, max = 200, message = "健身房名的长度必须介于 1 - 200 位之间")
  private String address;

}
