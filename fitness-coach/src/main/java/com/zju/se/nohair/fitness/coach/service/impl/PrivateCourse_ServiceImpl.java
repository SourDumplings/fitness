package com.zju.se.nohair.fitness.coach.service.impl;

import com.zju.se.nohair.fitness.coach.dto.CreatePrivateCourse_Dto;
import com.zju.se.nohair.fitness.coach.service.PrivateCourse_Service;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.PrivateCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.ResponsesPrivateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-24
 */
@Service
public class PrivateCourse_ServiceImpl implements PrivateCourse_Service {
  private static Logger logger = LoggerFactory.getLogger(PrivateCourse_ServiceImpl.class);

  private PrivateCourseMapper privateCourseMapper;

  private ResponsesPrivateMapper responsesPrivateMapper;

  @Autowired
  public void setPrivateCourseMapper(PrivateCourseMapper privateCourseMapper) {
    this.privateCourseMapper = privateCourseMapper;
  }

  @Autowired
  public void setResponsesPrivateMapper(ResponsesPrivateMapper responsesPrivateMapper) {
    this.responsesPrivateMapper = responsesPrivateMapper;
  }

  @Override
  public BaseResult createPrivateCourse(CreatePrivateCourse_Dto createPrivateCourseDto) {
    return null;
  }
}
