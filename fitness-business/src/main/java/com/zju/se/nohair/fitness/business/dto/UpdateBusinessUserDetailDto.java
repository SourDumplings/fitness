package com.zju.se.nohair.fitness.business.dto;

import com.zju.se.nohair.fitness.commons.utils.RegexpUtils;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

/**
 * 修改商家用户详情所需的 Dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/17 17:58
 */
@Component
@Data
public class UpdateBusinessUserDetailDto {

  private Integer id;

  @Length(min = 1, max = 20, message = "用户名的长度必须介于 1 - 20 位之间")
  private String username;

  @Length(min = 6, max = 20, message = "密码的长度必须介于 6 - 20 位之间")
  private String password;

  @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
  private String phone;

}
