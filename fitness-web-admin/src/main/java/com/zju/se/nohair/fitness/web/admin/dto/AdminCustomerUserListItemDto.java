package com.zju.se.nohair.fitness.web.admin.dto;

import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 后台模块的用户子模块下的顾客用户列表项 dto.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/19 16:09
 */
@Component
@Data
public class AdminCustomerUserListItemDto {

  private Integer id;

  private String username;

  private Date createdTime;
}
