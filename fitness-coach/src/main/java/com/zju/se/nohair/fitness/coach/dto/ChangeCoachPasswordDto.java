package com.zju.se.nohair.fitness.coach.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 更改教练登录密码Dto
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-24
 */
@Component
@Data
public class ChangeCoachPasswordDto {

  private Integer id;

  private String password;//原密码

  private String password1;//新密码

  private String password2;//再次确认新密码
}
