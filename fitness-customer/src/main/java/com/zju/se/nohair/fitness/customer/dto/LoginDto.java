package com.zju.se.nohair.fitness.customer.dto;

import org.springframework.stereotype.Component;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/3/27 21:04
 */
@Component
public class LoginDto {

  private String phone;

  private String password;

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
