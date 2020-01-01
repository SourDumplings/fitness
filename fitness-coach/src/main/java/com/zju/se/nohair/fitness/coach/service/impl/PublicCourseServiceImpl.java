package com.zju.se.nohair.fitness.coach.service.impl;

import com.zju.se.nohair.fitness.coach.dto.PublicCourseDetailDto;
import com.zju.se.nohair.fitness.coach.dto.PublicCourseListItemDto;
import com.zju.se.nohair.fitness.coach.dto.ResponseToPublicCourseDto;
import com.zju.se.nohair.fitness.coach.service.PublicCourseService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.mapper.PublicCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.ResponsesPublicMapper;
import com.zju.se.nohair.fitness.dao.po.PublicCoursePo;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 团课 service 实现类.
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-24
 */
@Service
public class PublicCourseServiceImpl implements PublicCourseService {
  private static Logger logger = LoggerFactory.getLogger(PublicCourseServiceImpl.class);

  private PublicCourseMapper publicCourseMapper;

  private ResponsesPublicMapper responsesPublicMapper;

  private CoachMapper coachMapper;

  private PictureMapper pictureMapper;

  @Autowired
  public void setPublicCourseMapper(PublicCourseMapper publicCourseMapper) {
    this.publicCourseMapper = publicCourseMapper;
  }

  @Autowired
  public void setResponsesPublicMapper(ResponsesPublicMapper responsesPublicMapper) {
    this.responsesPublicMapper = responsesPublicMapper;
  }

  @Autowired
  public void setCoachMapper(CoachMapper coachMapper) {
    this.coachMapper = coachMapper;
  }

  @Autowired
  public void setPictureMapper(PictureMapper pictureMapper) {
    this.pictureMapper = pictureMapper;
  }

  @Override
  public BaseResult listPublicCoursesByBusinessId(Integer businessId) {
    BaseResult res = null;

    try {
      final List<PublicCoursePo> publicCourses = publicCourseMapper.selectByBusinessId(businessId);
      List<PublicCourseListItemDto> publicCourseListItemDtoList = new ArrayList<>();
      for (PublicCoursePo publicCoursePo : publicCourses) {
        PublicCourseListItemDto publicCourseListItemDto = new PublicCourseListItemDto();
        BeanUtils.copyProperties(publicCoursePo, publicCourseListItemDto);
        publicCourseListItemDtoList.add(publicCourseListItemDto);
      }
      res = BaseResult.success("查询发布的课程列表成功");
      res.setData(publicCourseListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询发布的课程列表失败");
    }

    return res;
  }

  @Override
  public BaseResult getPublicCourseDetailByCourseId(Integer courseId) {
    BaseResult res = null;

    try {
      final PublicCoursePo publicCoursePo = publicCourseMapper.selectByPrimaryKey(courseId);
      PublicCourseDetailDto publicCourseDetailDto = new PublicCourseDetailDto();
      BeanUtils.copyProperties(publicCoursePo, publicCourseDetailDto);
      res = BaseResult.success("查询发布的课程详情成功");
      res.setData(publicCourseDetailDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询发布的课程详情失败");
    }

    return res;
  }



  @Override
  public BaseResult responseToPublicCourse(ResponseToPublicCourseDto responseToPublicCourseDto) {
    return null;
  }



 /* @Override
  public BaseResult listPublicCoursesForResponsing() {
    BaseResult res = null;

    try {
      final List<PublicCoursePo> publicCourses = publicCourseMapper
          .selectForResponsing();
      List<PublicCourseListItemDto> publicCourseListItemDtoList = new ArrayList<>();
      for (PublicCoursePo publicCourse : publicCourses) {
        PublicCourseListItemDto publicCourseListItemDto = new PublicCourseListItemDto();
        BeanUtils.copyProperties(publicCourse, publicCourseListItemDto);
        publicCourseListItemDtoList.add(publicCourseListItemDto);
      }
      res = BaseResult.success("查询等待教练响应的团课课程列表成功");
      res.setData(publicCourseListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询等待教练响应的团课课程列表失败");
    }

    return res;
  }*/

  /*@Override
  public BaseResult listResponsedPublicCoursesByCoachId(Integer coachId) {
    return null;
  }*/
}
