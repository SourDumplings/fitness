package com.zju.se.nohair.fitness.web.admin.dao.po;

import com.zju.se.nohair.fitness.commons.persistence.BaseEntity;
import java.io.Serializable;
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
public class User extends BaseEntity implements Serializable {
  private String username;
  private String phone;
  private String email;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("User{");
    sb.append("username='").append(username).append('\'');
    sb.append(", phone='").append(phone).append('\'');
    sb.append(", email='").append(email).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
