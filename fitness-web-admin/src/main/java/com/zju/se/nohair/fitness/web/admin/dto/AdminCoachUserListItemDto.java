package com.zju.se.nohair.fitness.web.admin.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 后台模块的用户子模块下的教练用户列表项 dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/19 15:17
 */
@Component
@Data
public class AdminCoachUserListItemDto {

  private Integer id;

  private String idNumber;

  private String name;

  private String phone;

  private Integer age;

  private Integer gender;
}
