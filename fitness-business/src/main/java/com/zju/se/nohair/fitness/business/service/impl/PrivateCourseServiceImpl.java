package com.zju.se.nohair.fitness.business.service.impl;

import com.zju.se.nohair.fitness.business.dto.PrivateCourseDetailDto;
import com.zju.se.nohair.fitness.business.dto.PrivateCourseListItemDto;
import com.zju.se.nohair.fitness.business.dto.PrivateCourseResponseDto;
import com.zju.se.nohair.fitness.business.dto.ResponseToPrivateCourseDto;
import com.zju.se.nohair.fitness.business.service.PrivateCourseService;
import com.zju.se.nohair.fitness.commons.constant.ResponseStatus;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.PrivateCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.ResponsesPrivateMapper;
import com.zju.se.nohair.fitness.dao.po.PrivateCoursePo;
import com.zju.se.nohair.fitness.dao.po.ResponsesPrivatePo;
import com.zju.se.nohair.fitness.dao.po.ResponsesPrivatePoKey;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 私教课 service 实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/17 13:41
 */
@Service
public class PrivateCourseServiceImpl implements PrivateCourseService {

  private static Logger logger = LoggerFactory.getLogger(PrivateCourseServiceImpl.class);

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
  public BaseResult listResponsesByBusinessId(Integer businessId) {
    BaseResult res = null;

    try {
      List<PrivateCourseResponseDto> privateCourseResponseDtoList = new ArrayList<>();
      final List<ResponsesPrivatePo> responsesPrivatePos = responsesPrivateMapper
          .selectByBusinessId(businessId);
      for (ResponsesPrivatePo responsesPrivatePo : responsesPrivatePos) {
        PrivateCourseResponseDto privateCourseResponseDto = new PrivateCourseResponseDto();
        BeanUtils.copyProperties(responsesPrivatePo, privateCourseResponseDto);
        privateCourseResponseDtoList.add(privateCourseResponseDto);
      }
      res = BaseResult.success("查询商家对于私教课的响应列表成功");
      res.setData(privateCourseResponseDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询商家对于私教课的响应列表失败");
    }

    return res;
  }

  @Override
  public BaseResult listPrivateCoursesForResponsing() {
    BaseResult res = null;

    try {
      final List<PrivateCoursePo> privateCourses = privateCourseMapper
          .selectForResponsing();
      List<PrivateCourseListItemDto> privateCourseListItemDtoList = new ArrayList<>();
      for (PrivateCoursePo privateCourse : privateCourses) {
        PrivateCourseListItemDto privateCourseListItemDto = new PrivateCourseListItemDto();
        BeanUtils.copyProperties(privateCourse, privateCourseListItemDto);
      }
      res = BaseResult.success("查询等待商家响应的私教课程列表成功");
      res.setData(privateCourseListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询等待商家响应的私教课程列表失败");
    }

    return res;
  }


  @Override
  public BaseResult listResponsedPrivateCoursesByBusinessId(Integer businessId) {
    BaseResult res = null;

    try {
      List<PrivateCourseListItemDto> privateCourseListItemDtoList = new ArrayList<>();
      final List<PrivateCoursePo> privateCoursePos = privateCourseMapper
          .selectByBusinessId(businessId);
      for (PrivateCoursePo privateCoursePo : privateCoursePos) {
        PrivateCourseListItemDto privateCourseListItemDto = new PrivateCourseListItemDto();
        BeanUtils.copyProperties(privateCoursePo, privateCourseListItemDto);
        privateCourseListItemDtoList.add(privateCourseListItemDto);
      }
      res = BaseResult.success("查询商家响应成功的私教课程列表成功");
      res.setData(privateCourseListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询商家响应成功的私教课程列表失败");
    }

    return res;
  }

  @Override
  public BaseResult getPrivateCourseDetailByCourseId(Integer courseId) {
    BaseResult res = null;

    try {
      final PrivateCoursePo privateCoursePo = privateCourseMapper.selectByPrimaryKey(courseId);
      final PrivateCourseDetailDto privateCourseDetailDto = new PrivateCourseDetailDto();
      BeanUtils.copyProperties(privateCoursePo, privateCourseDetailDto);
      res = BaseResult.success("查询私教课程详情成功");
      res.setData(privateCourseDetailDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询私教课程详情失败");
    }

    return res;
  }

  @Override
  public BaseResult responseToPrivateCourse(ResponseToPrivateCourseDto responseToPrivateCourseDto) {
    BaseResult res = null;

    try {
      ResponsesPrivatePo responsesPrivatePo = new ResponsesPrivatePo();
      PrivateCoursePo privateCoursePo = privateCourseMapper
          .selectByPrimaryKey(responseToPrivateCourseDto.getCourseId());

      if (responseToPrivateCourseDto.getPrice().compareTo(privateCoursePo.getPrice()) >= 0) {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "商家场地费的价格不能超过课程的价格");
        return res;
      }

      BeanUtils.copyProperties(responseToPrivateCourseDto, responsesPrivatePo);
      responsesPrivatePo.setStatus(ResponseStatus.NEW_PUBLISH);
      responsesPrivateMapper.insert(responsesPrivatePo);
      res = BaseResult.success("响应私教课程成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("响应私教课程失败");
    }

    return res;
  }

  @Override
  public BaseResult deleteResponse(Integer courseId, Integer businessId) {
    BaseResult res = null;

    try {
      ResponsesPrivatePoKey responsesPrivatePoKey = new ResponsesPrivatePoKey();
      responsesPrivatePoKey.setBusinessId(businessId);
      responsesPrivatePoKey.setCourseId(courseId);
      final ResponsesPrivatePo responsesPrivatePo = responsesPrivateMapper
          .selectByPrimaryKey(responsesPrivatePoKey);

      if (responsesPrivatePo.getStatus().equals(ResponseStatus.ACCEPTED)) {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "不能删除已经被接受的响应");
        return res;
      }

      responsesPrivateMapper.deleteByPrimaryKey(responsesPrivatePoKey);
      res = BaseResult.success("删除响应成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("删除响应失败");
    }

    return res;
  }
}
