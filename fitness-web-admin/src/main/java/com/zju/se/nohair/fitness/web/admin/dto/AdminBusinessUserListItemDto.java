package com.zju.se.nohair.fitness.web.admin.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 后台模块的用户子模块下的商家用户列表项 dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/16 9:35
 */
@Component
@Data
public class AdminBusinessUserListItemDto {

  private Integer id;

  private String idNumber;

  private String personName;

  private String phone;

  private String gymName;
}