package com.zju.se.nohair.fitness.coach.dto;

import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 教练端查看附近健身房
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-20
 */
@Component
@Data
public class GymDto {

  private Integer id;

  private String name;//健身房名字

  private String address;//健身房地址

  private String content;//健身房简介

  private Integer businessId;//商家id

  private List<String> picGroupId;//健身房图片组

}
