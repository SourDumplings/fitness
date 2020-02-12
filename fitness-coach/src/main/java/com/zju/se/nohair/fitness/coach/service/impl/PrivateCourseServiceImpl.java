package com.zju.se.nohair.fitness.coach.service.impl;


import com.zju.se.nohair.fitness.coach.dto.CreatePrivateCourseDto;
import com.zju.se.nohair.fitness.coach.dto.PrivateCourseDetailDto;
import com.zju.se.nohair.fitness.coach.dto.PrivateCourseListItemDto;
import com.zju.se.nohair.fitness.coach.dto.PrivateCourseResponseDto;
import com.zju.se.nohair.fitness.coach.service.PrivateCourseService;
import com.zju.se.nohair.fitness.commons.constant.PrivateCourseStatus;
import com.zju.se.nohair.fitness.commons.constant.ResponseStatus;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.commons.utils.DateUtils;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.GymMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.mapper.PrivateCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.ResponsesPrivateMapper;
import com.zju.se.nohair.fitness.dao.po.PrivateCoursePo;
import com.zju.se.nohair.fitness.dao.po.ResponsesPrivatePo;
import com.zju.se.nohair.fitness.dao.po.ResponsesPrivatePoKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-24
 */
@Transactional(readOnly = true)
@Service
public class PrivateCourseServiceImpl implements PrivateCourseService {
  private static Logger logger = LoggerFactory.getLogger(PrivateCourseServiceImpl.class);

  private PrivateCourseMapper privateCourseMapper;

  private ResponsesPrivateMapper responsesPrivateMapper;

  private GymMapper gymMapper;

  private BusinessMapper businessMapper;

  private PictureMapper pictureMapper;

  @Autowired
  public void setPrivateCourseMapper(PrivateCourseMapper privateCourseMapper) {
    this.privateCourseMapper = privateCourseMapper;
  }

  @Autowired
  public void setResponsesPrivateMapper(ResponsesPrivateMapper responsesPrivateMapper) {
    this.responsesPrivateMapper = responsesPrivateMapper;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult createPrivateCourse(CreatePrivateCourseDto createPrivateCourseDto) {
    //教练发布私教课
    BaseResult res = null;

    try {
      PrivateCoursePo privateCoursePo = new PrivateCoursePo();

      BeanUtils.copyProperties(createPrivateCourseDto, privateCoursePo);
      privateCoursePo.setCreatedTime(new Date());
      privateCoursePo.setId(null);
      privateCoursePo.setBusinessId(null);
      privateCoursePo.setStatus(PrivateCourseStatus.NEW_PUBLISH);

      privateCourseMapper.insert(privateCoursePo);
      res = BaseResult.success("发布私教课程成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("发布私教课程失败");
    }

    return res;
  }

  @Override
  public BaseResult listPrivateCourseResponsesByCourseId(Integer courseId) {
    //通过课程id 查找响应私教课的商家列表
    BaseResult res = null;

    try {
      final List<ResponsesPrivatePo> responsesPrivatePos = responsesPrivateMapper.selectByCourseId(courseId);
      List<PrivateCourseResponseDto> privateCourseResponseDtoList = new ArrayList<>();
      for (ResponsesPrivatePo responsesPrivatePo : responsesPrivatePos) {
        PrivateCourseResponseDto privateCourseResponseDto = new PrivateCourseResponseDto();
        BeanUtils.copyProperties(responsesPrivatePo, privateCourseResponseDto);

        privateCourseResponseDtoList.add(privateCourseResponseDto);
      }
      res = BaseResult.success("查找响应私教课的商家列表成功");
      res.setData(privateCourseResponseDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查找响应私教课的商家列表失败");
    }

    return res;
  }



  @Override
  public BaseResult acceptResponse(Integer courseId, Integer businessId) {
    //接受商家对于私教课的响应
    BaseResult res = null;

    try {
      ResponsesPrivatePoKey responsesPrivatePoKey = new ResponsesPrivatePoKey();
      responsesPrivatePoKey.setCourseId(courseId);
      responsesPrivatePoKey.setBusinessId(businessId);
      ResponsesPrivatePo responsesPrivatePo = responsesPrivateMapper
          .selectByPrimaryKey(responsesPrivatePoKey);

      if (responsesPrivatePo.getStatus().equals(ResponseStatus.ACCEPTED)) {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "该响应已经被接受");
      } else {
        PrivateCoursePo privateCoursePo = privateCourseMapper.selectByPrimaryKey(courseId);

        if (!privateCoursePo.getStatus().equals(PrivateCourseStatus.NEW_PUBLISH)) {
          res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "该课程的教练已经确定");
        } else {
          responsesPrivatePo.setStatus(ResponseStatus.ACCEPTED);
          responsesPrivateMapper.updateByPrimaryKey(responsesPrivatePo);

          privateCoursePo.setStatus(PrivateCourseStatus.BUSINESS_SELECTED);
          privateCoursePo.setCoachId(businessId);
          privateCoursePo.setGymPrice(responsesPrivatePo.getPrice());
          privateCourseMapper.updateByPrimaryKey(privateCoursePo);

          res = BaseResult.success("接受响应成功");
        }
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("接受响应失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult deletePrivateCourseByCourseId(Integer courseId) {
    //删除发布的私教课
    BaseResult res = null;

    try {
      final PrivateCoursePo privateCoursePo = privateCourseMapper.selectByPrimaryKey(courseId);
      final Integer status = privateCoursePo.getStatus();
      if (PrivateCourseStatus.NEW_PUBLISH.equals(status) || status
          .equals(PrivateCourseStatus.BUSINESS_SELECTED)) {
        privateCourseMapper.deleteByPrimaryKey(courseId);
        res = BaseResult.success("删除私教课程成功");
      } else {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "不能删除已经有顾客选定的课程");
      }
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("删除私教课程失败");
    }

    return res;
  }

  @Override
  public BaseResult listPrivateCourses(Integer coachId, Date courseDate) {
    //查看教练发布的私教课列表
    BaseResult res = null;

    try {
      final List<PrivateCoursePo> privateCourses = privateCourseMapper.selectByCoachId(coachId);
      List<PrivateCourseListItemDto> privateCourseListItemDtoList = new ArrayList<>();
      for (PrivateCoursePo privateCoursePo : privateCourses) {
        if (courseDate != null) {
          if (!DateUtils.sameDate(privateCoursePo.getCourseDate(), courseDate)
              || privateCoursePo.getStatus().equals(PrivateCourseStatus.NEW_PUBLISH)) {
            continue;
          }
        }

        PrivateCourseListItemDto privateCourseListItemDto = new PrivateCourseListItemDto();
        BeanUtils.copyProperties(privateCoursePo, privateCourseListItemDto);
        privateCourseListItemDtoList.add(privateCourseListItemDto);
      }
      res = BaseResult.success("查询教练发布的私教课程列表成功");
      res.setData(privateCourseListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询教练发布的私教课程列表失败");
    }

    return res;
  }

  @Override
  public BaseResult getPrivateCourseDetailByCourseId(Integer courseId) {
    //查看发布的私教课详情
    BaseResult res = null;

    try {
      final PrivateCoursePo privateCoursePo = privateCourseMapper.selectByPrimaryKey(courseId);
      PrivateCourseDetailDto privateCourseDetailDto = new PrivateCourseDetailDto();
      BeanUtils.copyProperties(privateCoursePo, privateCourseDetailDto);

      res = BaseResult.success("查看发布的私教课程详情成功");
      res.setData(privateCourseDetailDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查看发布的私教课程详情失败");
    }

    return res;
  }

  @Override
  public BaseResult listFinishedPrivateCourses(Integer coachId) {
    //查看教练结课私教课列表
    BaseResult res = null;

    try {
      final List<PrivateCoursePo> privateCourses = privateCourseMapper.selectFinishedPrivateCoursesByCoachId(coachId);
      List<PrivateCourseListItemDto> privateCourseListItemDtoList = new ArrayList<>();
      for (PrivateCoursePo privateCoursePo : privateCourses) {
        PrivateCourseListItemDto privateCourseListItemDto = new PrivateCourseListItemDto();
        BeanUtils.copyProperties(privateCoursePo, privateCourseListItemDto);
        privateCourseListItemDtoList.add(privateCourseListItemDto);
      }
      res = BaseResult.success("查看教练结课私教课列表成功");
      res.setData(privateCourseListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查看教练结课私教课列表失败");
    }
    return res;
  }

  @Override
  public BaseResult listRequiredPrivateCourses(Integer coachId) {
    //查看教练待上私教课列表
    BaseResult res = null;

    try {
      final List<PrivateCoursePo> privateCourses = privateCourseMapper.selectRequiredPrivateCoursesByCoachId(coachId);
      List<PrivateCourseListItemDto> privateCourseListItemDtoList = new ArrayList<>();
      for (PrivateCoursePo privateCoursePo : privateCourses) {
        PrivateCourseListItemDto privateCourseListItemDto = new PrivateCourseListItemDto();
        BeanUtils.copyProperties(privateCoursePo, privateCourseListItemDto);
        privateCourseListItemDtoList.add(privateCourseListItemDto);
      }
      res = BaseResult.success("查询教练待上私教课列表成功");
      res.setData(privateCourseListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询教练待上私教课列表失败");
    }
    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult finishPrivateCourseByCourseId(Integer courseId) {
    //私教课结课
    BaseResult res = null;
    try {
      final PrivateCoursePo privateCoursePo = privateCourseMapper.selectByPrimaryKey(courseId);
      final Integer status = privateCoursePo.getStatus();
      if (PrivateCourseStatus.NEW_PUBLISH.equals(status) ||
          status.equals(PrivateCourseStatus.BUSINESS_SELECTED )||
          status.equals(PrivateCourseStatus.CUSTOMER_PAID)) {
        privateCourseMapper.finishByPrimaryKey(courseId);
        res = BaseResult.success("私教课结课成功");
      } else {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "该课程已结束");
      }
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("私教课结课失败");
    }

    return res;
  }

  @Override
  public BaseResult getPrivateCourseDetailByCoachId(Integer coachId) {
    //查看待响应的私教课详情
    BaseResult res = null;

    try {
      final PrivateCoursePo privateCoursePo = privateCourseMapper.selectRequiredByCoachId(coachId);
      PrivateCourseDetailDto privateCourseDetailDto = new PrivateCourseDetailDto();
      BeanUtils.copyProperties(privateCoursePo, privateCourseDetailDto);

      res = BaseResult.success("查看发布的私教课程详情成功");
      res.setData(privateCourseDetailDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查看发布的私教课程详情失败");
    }

    return res;
  }
}
