package com.zju.se.nohair.fitness.business.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author changzheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/3/25 10:04
 */
@Component
@Data
public class ReadNotificationDto
{

    private Integer fromId;

    private Integer toId;

    private String timeStr;

    private Integer type;
}
