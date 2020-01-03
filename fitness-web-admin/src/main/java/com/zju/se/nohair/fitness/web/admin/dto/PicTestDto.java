package com.zju.se.nohair.fitness.web.admin.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 用于测试上传图片 同时传参的dto
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/3 11:49
 */
@Component
@Data
public class PicTestDto {

  private Integer id;

  private String name;

}
