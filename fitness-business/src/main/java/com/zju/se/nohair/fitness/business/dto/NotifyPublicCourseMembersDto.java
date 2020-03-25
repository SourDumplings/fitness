package com.zju.se.nohair.fitness.business.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author changzheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/3/25 10:22
 */
@Data
@Component
public class NotifyPublicCourseMembersDto {

  private Integer publicCourseId;

  private String name;

  private String content;

  private Integer type;
}
