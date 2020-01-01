package com.zju.se.nohair.fitness.coach.service.impl;

import com.zju.se.nohair.fitness.coach.dto.CreatePrivateCourseDto;
import com.zju.se.nohair.fitness.coach.dto.PrivateCourseResponseDto;
import com.zju.se.nohair.fitness.coach.service.PrivateCourseService;
import com.zju.se.nohair.fitness.commons.constant.PrivateCourseStatus;
import com.zju.se.nohair.fitness.commons.constant.ResponseStatus;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.mapper.PrivateCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.ResponsesPrivateMapper;
import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import com.zju.se.nohair.fitness.dao.po.PicturePo;
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
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2019-12-24
 */
@Service
public class PrivateCourseServiceImpl implements PrivateCourseService {
  private static Logger logger = LoggerFactory.getLogger(PrivateCourseServiceImpl.class);

  private PrivateCourseMapper privateCourseMapper;

  private ResponsesPrivateMapper responsesPrivateMapper;

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

  @Override
  public BaseResult createPrivateCourse(CreatePrivateCourseDto createPrivateCourseDto) {
    return null;
  }

  /*@Override
  public BaseResult listPrivateCourseResponsesByCourseId(Integer courseId) {
    //通过课程id 查找响应私教课的商家列表
    BaseResult res = null;
    try {
      final List<ResponsesPrivatePo> responsesPrivatePos = responsesPrivateMapper.selectByCourseId(courseId);
      List<PrivateCourseResponseDto> privateCourseResponseDtoList = new ArrayList<>();
      for (ResponsesPrivatePo responsesPrivatePo : responsesPrivatePos) {
        PrivateCourseResponseDto privateCourseResponseDto = new PrivateCourseResponseDto();
        BeanUtils.copyProperties(responsesPrivatePo, privateCourseResponseDto);

        final BusinessPo businessPo = businessMapper.selectByPrimaryKey(responsesPrivatePo.getBusinessId());
        privateCourseResponseDto.setBusinessName(businessPo.getUsername());

        if (businessPo.getPicId() != null) {
          final PicturePo picturePo = pictureMapper.selectByPrimaryKey(businessPo.getPicId());
          privateCourseResponseDto.setBusinessProfilePic(picturePo.getPicLink());
        } else {
          privateCourseResponseDto.setBusinessProfilePic(null);
        }

        privateCourseResponseDtoList.add(privateCourseResponseDto);
      }
      res = BaseResult.success("查询课程的响应列表成功");
      res.setData(privateCourseResponseDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询课程的响应列表失败");
    }

    return res;
  }*/

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
}
