package com.zju.se.nohair.fitness.business.service.impl;

import com.zju.se.nohair.fitness.business.dto.CreatePublicCourseDto;
import com.zju.se.nohair.fitness.business.dto.PublicCourseDetailDto;
import com.zju.se.nohair.fitness.business.dto.PublicCourseListItemDto;
import com.zju.se.nohair.fitness.business.dto.PublicCourseResponseDto;
import com.zju.se.nohair.fitness.business.service.PublicCourseService;
import com.zju.se.nohair.fitness.commons.constant.PublicCourseStatus;
import com.zju.se.nohair.fitness.commons.constant.ResponseStatus;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.dao.po.PicturePo;
import com.zju.se.nohair.fitness.dao.po.PublicCoursePo;
import com.zju.se.nohair.fitness.dao.po.ResponsesPublicPo;
import com.zju.se.nohair.fitness.dao.po.ResponsesPublicPoKey;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.mapper.PublicCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.ResponsesPublicMapper;
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
      publicCoursePo.setStatus(PublicCourseStatus.NEW_PUBLISH);

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
    BaseResult res = null;

    try {
      final PublicCoursePo publicCoursePo = publicCourseMapper.selectByPrimaryKey(courseId);
      final Integer status = publicCoursePo.getStatus();
      if (PublicCourseStatus.NEW_PUBLISH.equals(status) || status
          .equals(PublicCourseStatus.COACH_SELECTED)) {
        publicCourseMapper.deleteByPrimaryKey(courseId);
        res = BaseResult.success("删除该发布的课程成功");
      } else {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "不能删除已经有顾客选定的课程");
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("删除该发布的课程失败");
    }

    return res;
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

  @Override
  public BaseResult acceptResponse(Integer courseId, Integer coachId) {
    BaseResult res = null;

    try {
      ResponsesPublicPoKey responsesPublicPoKey = new ResponsesPublicPoKey();
      responsesPublicPoKey.setCourseId(courseId);
      responsesPublicPoKey.setCoachId(coachId);
      ResponsesPublicPo responsesPublicPo = responsesPublicMapper
          .selectByPrimaryKey(responsesPublicPoKey);

      if (responsesPublicPo.getStatus().equals(ResponseStatus.ACCEPTED)) {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "该响应已经被接受");
      } else {
        PublicCoursePo publicCoursePo = publicCourseMapper.selectByPrimaryKey(courseId);

        if (!publicCoursePo.getStatus().equals(PublicCourseStatus.NEW_PUBLISH)) {
          res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "该课程的教练已经确定");
        } else {
          responsesPublicPo.setStatus(ResponseStatus.ACCEPTED);
          responsesPublicMapper.updateByPrimaryKey(responsesPublicPo);

          publicCoursePo.setStatus(PublicCourseStatus.COACH_SELECTED);
          publicCoursePo.setCoachId(coachId);
          publicCoursePo.setCoachPrice(responsesPublicPo.getPrice());
          publicCourseMapper.updateByPrimaryKey(publicCoursePo);

          res = BaseResult.success("接受响应成功");
        }
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("接受响应失败");
    }

    return res;
  }

  @Override
  public BaseResult denyResponse(Integer courseId, Integer coachId) {
    BaseResult res = null;

    try {
      ResponsesPublicPoKey responsesPublicPoKey = new ResponsesPublicPoKey();
      responsesPublicPoKey.setCourseId(courseId);
      responsesPublicPoKey.setCoachId(coachId);
      ResponsesPublicPo responsesPublicPo = responsesPublicMapper
          .selectByPrimaryKey(responsesPublicPoKey);

      if (!responsesPublicPo.getStatus().equals(ResponseStatus.DENIED)) {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "该响应已经被处理");
      } else {
        responsesPublicPo.setStatus(ResponseStatus.DENIED);
        responsesPublicMapper.updateByPrimaryKey(responsesPublicPo);
        res = BaseResult.success("拒绝响应成功");
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("拒绝响应失败");
    }

    return res;
  }
}
