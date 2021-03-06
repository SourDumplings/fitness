package com.zju.se.nohair.fitness.coach.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 教练查看商家对私教课的响应
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-20
 */
@Component
@Data
public class PrivateCourseResponseDto {
//响应商家id，健身房名字，地址，健身房价格，健身房图片，响应健身房数量，开课时间
  private Integer businessId;//响应商家id

  private String name;//商家名字

  private String address;//健身房地址

  //private String businessProfilePic;//商家资格证

  private BigDecimal price;//健身房价格

  //private String picGroupId;//健身房图片组

  //private Date courseDate;//上课日期

  private Integer status;//状态

}
