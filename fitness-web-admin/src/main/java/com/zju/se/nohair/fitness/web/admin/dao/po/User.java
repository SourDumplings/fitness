package com.zju.se.nohair.fitness.web.admin.dao.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zju.se.nohair.fitness.commons.persistence.BaseEntity;
import com.zju.se.nohair.fitness.commons.utils.RegexpUtils;
import java.io.Serializable;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

/**
 * 用户实体类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/21 14:46
 */
@Component
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity implements Serializable {

  @Length(min = 6, max = 20, message = "姓名的长度必须介于 6 - 20 位之间")
  private String username;

  @JsonIgnore
  private String password;

  @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
  private String phone;

  @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
  private String email;
}
