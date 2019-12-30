package com.zju.se.nohair.fitness.customer.dto;

import com.zju.se.nohair.fitness.dao.po.TimeSlotPo;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
public class PrivateCourseItemOfListDto {

  private Integer id;

  private String name;

  private String content;

  private Integer coachId;

  private Integer businessId;

  private BigDecimal price;

  private Integer picId;

  private Date courseDate;

  private Integer status;

  private Integer gymId;

  private String gymName;

  private String coachName;

  private Integer coachPicId;

  private BigDecimal avgRating;

  private List<TimeSlotPo> timeSlots;

}
