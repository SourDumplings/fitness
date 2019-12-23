package com.zju.se.nohair.fitness.customer.dto;

import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 给用户看的团课列表的单项信息的dto
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/17 8:56
 */

@Component
@Data
public class PublicCourseItemOfListDto {

  private Integer id;

  private String name;

  private String content;

  private Integer businessId;

  private Integer coachId;

  private Double price;

  private Integer picId;

  private Integer capacity;

  private Date courseDate;

  private Integer timeSlotId;

  private Integer status;
}
