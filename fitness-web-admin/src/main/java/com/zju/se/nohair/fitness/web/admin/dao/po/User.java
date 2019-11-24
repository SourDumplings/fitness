package com.zju.se.nohair.fitness.web.admin.dao.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zju.se.nohair.fitness.commons.persistence.BaseEntity;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
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

  private String username;
  @JsonIgnore
  private String password;
  private String phone;
  private String email;
}
