package com.zju.se.nohair.fitness.business.service.impl;

import com.zju.se.nohair.fitness.business.dto.CreatePublicCourseDto;
import com.zju.se.nohair.fitness.business.service.PublicCourseService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.PublicCourseMapper;
import com.zju.se.nohair.fitness.dao.po.PublicCoursePo;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 团课 service 实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/11 8:38
 */
@Service
public class PublicCourseServiceImpl implements PublicCourseService {

  private static Logger logger = LoggerFactory.getLogger(PublicCourseServiceImpl.class);

  private PublicCourseMapper publicCourseMapper;

  @Autowired
  public void setPublicCourseMapper(PublicCourseMapper publicCourseMapper) {
    this.publicCourseMapper = publicCourseMapper;
  }

  @Override
  public BaseResult createPublicCourse(CreatePublicCourseDto createPublicCourseDto) {
    BaseResult res = null;

    try {
      PublicCoursePo publicCoursePo = new PublicCoursePo();

      BeanUtils.copyProperties(createPublicCourseDto, publicCoursePo);
      publicCoursePo.setCreatedTime(new Date());
      publicCoursePo.setId(null);
      publicCoursePo.setCoachId(null);
      publicCoursePo.setStatus(0);

      publicCourseMapper.insert(publicCoursePo);
      res = BaseResult.success("发布课程成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("发布课程失败");
    }

    return res;
  }
}
