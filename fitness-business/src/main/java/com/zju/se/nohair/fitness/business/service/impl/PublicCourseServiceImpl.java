package com.zju.se.nohair.fitness.business.service.impl;

import com.zju.se.nohair.fitness.business.dto.CreatePublicCourseDto;
import com.zju.se.nohair.fitness.business.dto.PublicCourseDetailDto;
import com.zju.se.nohair.fitness.business.dto.PublicCourseListItemDto;
import com.zju.se.nohair.fitness.business.dto.PublicCourseResponseDto;
import com.zju.se.nohair.fitness.business.service.PublicCourseService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.mapper.PublicCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.ResponsesPublicMapper;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.dao.po.PicturePo;
import com.zju.se.nohair.fitness.dao.po.PublicCoursePo;
import com.zju.se.nohair.fitness.dao.po.ResponsesPublicPo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

  @Override
  public BaseResult deletePublicCourseByCourseId(Integer courseId) {
    return null;
  }

  @Override
  public BaseResult listPublicCoursesByBusinessId(Integer businessId) {
    BaseResult res = null;

    try {
      final List<PublicCoursePo> publicCourses = publicCourseMapper
          .selectByBusinessId(businessId);
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
  public BaseResult listPublicCourseResponsesByCourseId(Integer courseId) {
    BaseResult res = null;

    try {
      final List<ResponsesPublicPo> responsesPublicPos = responsesPublicMapper
          .selectByCourseId(courseId);
      List<PublicCourseResponseDto> publicCourseResponseDtoList = new ArrayList<>();
      for (ResponsesPublicPo responsesPublicPo : responsesPublicPos) {
        PublicCourseResponseDto publicCourseResponseDto = new PublicCourseResponseDto();
        BeanUtils.copyProperties(responsesPublicPo, publicCourseResponseDto);

        final CoachPo coachPo = coachMapper.selectByPrimaryKey(responsesPublicPo.getCoachId());
        publicCourseResponseDto.setCoachName(coachPo.getName());

        if (coachPo.getPicId() != null) {
          final PicturePo picturePo = pictureMapper.selectByPrimaryKey(coachPo.getPicId());
          publicCourseResponseDto.setCoachProfilePic(picturePo.getPicLink());
        } else {
          publicCourseResponseDto.setCoachProfilePic(null);
        }

        publicCourseResponseDtoList.add(publicCourseResponseDto);
      }
      res = BaseResult.success("查询课程的响应列表成功");
      res.setData(publicCourseResponseDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询课程的响应列表失败");
    }

    return res;
  }
}
