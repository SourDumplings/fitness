package com.zju.se.nohair.fitness.coach.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 课程评价Dto
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-21
 */
@Component
@Data
public class CommentCourseDto {

  private Integer fromId;

  private Integer toId;

  private Integer ratingPoints;

  private String content;

  private Integer type;

  private Integer courseId;
}
