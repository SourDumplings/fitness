package com.zju.se.nohair.fitness.business.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/3/28 20:22
 */
@Component
@Data
public class ChangePasswordDto {

  private String username;

  private String oldPassword;

  private String newPassword;
}
