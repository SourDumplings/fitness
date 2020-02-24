package com.zju.se.nohair.fitness.business.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/2/24 14:29
 */
@Component
@Data
public class TakesPublicCourseListItemDto {

  private Integer customerId;

  private String username;
}
