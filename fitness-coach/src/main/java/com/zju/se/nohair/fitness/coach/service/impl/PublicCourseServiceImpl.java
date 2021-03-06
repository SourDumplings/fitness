package com.zju.se.nohair.fitness.coach.service.impl;

import com.zju.se.nohair.fitness.coach.dto.CommentCourseDto;
import com.zju.se.nohair.fitness.coach.dto.PublicCourseDetailDto;
import com.zju.se.nohair.fitness.coach.dto.PublicCourseListItemDto;
import com.zju.se.nohair.fitness.coach.dto.PublicCourseResponseListItemDto;
import com.zju.se.nohair.fitness.coach.dto.ResponseToPublicCourseDto;
import com.zju.se.nohair.fitness.coach.service.PublicCourseService;
import com.zju.se.nohair.fitness.commons.constant.PublicCourseStatus;
import com.zju.se.nohair.fitness.commons.constant.ResponseStatus;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.commons.utils.DateUtils;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.GymMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.mapper.PublicCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.RatesMapper;
import com.zju.se.nohair.fitness.dao.mapper.ReceiveRecordMapper;
import com.zju.se.nohair.fitness.dao.mapper.ResponsesPublicMapper;
import com.zju.se.nohair.fitness.dao.mapper.TakesPublicMapper;
import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import com.zju.se.nohair.fitness.dao.po.GymPo;
import com.zju.se.nohair.fitness.dao.po.PicturePo;
import com.zju.se.nohair.fitness.dao.po.PublicCoursePo;
import com.zju.se.nohair.fitness.dao.po.RatesPo;
import com.zju.se.nohair.fitness.dao.po.ReceiveRecordPo;
import com.zju.se.nohair.fitness.dao.po.ResponsesPublicPo;
import com.zju.se.nohair.fitness.dao.po.ResponsesPublicPoKey;
import com.zju.se.nohair.fitness.dao.po.TakesPublicPoKey;
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

  private BusinessMapper businessMapper;

  private PictureMapper pictureMapper;

  private RatesMapper ratesMapper;

  private GymMapper gymMapper;

  private ReceiveRecordMapper receiveRecordMapper;

  private TakesPublicMapper takesPublicMapper;

  @Autowired
  public void setTakesPublicMapper(TakesPublicMapper takesPublicMapper) {
    this.takesPublicMapper = takesPublicMapper;
  }

  @Autowired
  public void setReceiveRecordMapper(ReceiveRecordMapper receiveRecordMapper) {
    this.receiveRecordMapper = receiveRecordMapper;
  }

  @Autowired
  public void setGymMapper(GymMapper gymMapper) {
    this.gymMapper = gymMapper;
  }

  @Autowired
  public void setBusinessMapper(BusinessMapper businessMapper) { this.businessMapper = businessMapper; }

  @Autowired
  public void setRatesMapper(RatesMapper ratesMapper) { this.ratesMapper = ratesMapper; }

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
  public BaseResult createCommentForPublicCourse(CommentCourseDto commentCourseDto) {
    //团课中教练评论商家
    BaseResult res = null;

    try {

      PublicCoursePo publicCoursePo = publicCourseMapper.selectByPrimaryKey(commentCourseDto.getCourseId());
      if (publicCoursePo.getStatus() == PublicCourseStatus.END_WITHOUT_EVALUATION){
        PublicCoursePo temp = new PublicCoursePo();
        temp.setId(commentCourseDto.getCourseId());
        temp.setStatus(PublicCourseStatus.EVALUATED);
        publicCourseMapper.updateByPrimaryKeySelective(temp);
      }
      RatesPo ratesPo = new RatesPo();
      BeanUtils.copyProperties(commentCourseDto, ratesPo);
      ratesPo.setTime(new Date());
      ratesMapper.insert(ratesPo);

      res = BaseResult.success("团课中教练评论商家成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("团课中教练评论商家失败");
    }

    return res;
  }

  @Override
  public BaseResult getPublicCourseDetailByCourseId(Integer courseId) {
    //查看发布的团课详情
    BaseResult res = null;

    try {
      final PublicCoursePo publicCoursePo = publicCourseMapper.selectByPrimaryKey(courseId);
      PublicCourseDetailDto publicCourseDetailDto = new PublicCourseDetailDto();
      BeanUtils.copyProperties(publicCoursePo, publicCourseDetailDto);

      final BusinessPo businessPo = businessMapper.selectByPrimaryKey(publicCoursePo.getBusinessId());
      if (businessPo.getPicId() != null) {
        final PicturePo picturePo = pictureMapper.selectByPrimaryKey(businessPo.getPicId());
        publicCourseDetailDto.setPicId(picturePo.getPicLink());
      } else {
        publicCourseDetailDto.setPicId(null);
      }

      res = BaseResult.success("查看发布的团课详情成功");
      res.setData(publicCourseDetailDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查看发布的团课详情失败");
    }

    return res;
  }



  @Override
  public BaseResult responseToPublicCourse(ResponseToPublicCourseDto responseToPublicCourseDto) {
    //教练响应团课
    BaseResult res = null;

    try {
      ResponsesPublicPo responsesPublicPo = new ResponsesPublicPo();
      PublicCoursePo publicCoursePo = publicCourseMapper
          .selectByPrimaryKey(responseToPublicCourseDto.getCourseId());

      if (responseToPublicCourseDto.getPrice().compareTo(publicCoursePo.getPrice()) >= 0) {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "教练费的价格不能超过课程的价格");
        return res;
      }

      BeanUtils.copyProperties(responseToPublicCourseDto, responsesPublicPo);
      responsesPublicPo.setStatus(ResponseStatus.NEW_PUBLISH);
      responsesPublicMapper.insert(responsesPublicPo);
      res = BaseResult.success("教练响应团课成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("教练响应团课失败");
    }

    return res;
  }

  @Override
  public BaseResult listPublicCoursesForResponsing(Date courseDate) {
    //不传 coachId 表示查看待响应的团课列表
    BaseResult res = null;

    try {
      final List<PublicCoursePo> publicCourses = publicCourseMapper.selectForResponsing();
      List<PublicCourseListItemDto> publicCourseListItemDtoList = new ArrayList<>();
      for (PublicCoursePo publicCoursePo : publicCourses) {
        if (courseDate != null && !DateUtils.sameDate(publicCoursePo.getCourseDate(), courseDate)) {
          continue;
        }

        PublicCourseListItemDto publicCourseListItemDto = new PublicCourseListItemDto();
        BeanUtils.copyProperties(publicCoursePo, publicCourseListItemDto);

        final BusinessPo businessPo = businessMapper.selectByPrimaryKey(publicCoursePo.getBusinessId());
        if (businessPo.getPicId() != null) {
          final PicturePo picturePo = pictureMapper.selectByPrimaryKey(businessPo.getPicId());
          publicCourseListItemDto.setPicId(picturePo.getPicLink());
        } else {
          publicCourseListItemDto.setPicId(null);
        }
        final GymPo gymPo = gymMapper.selectByBusinessId(publicCoursePo.getBusinessId());
        publicCourseListItemDto.setGymName(gymPo.getName());
        publicCourseListItemDtoList.add(publicCourseListItemDto);
      }
      res = BaseResult.success("查询等待教练响应的团课列表成功");
      res.setData(publicCourseListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询等待教练响应的团课列表失败");
    }

    return res;
  }

  @Override
  public BaseResult listResponsedPublicCourses(Integer coachId, Date courseDate) {
    //传了 coachsId 代表查看该教练响应成功的团课列表
    BaseResult res = null;

    try {
      List<PublicCourseListItemDto> publicCourseListItemDtoList = new ArrayList<>();
      final List<PublicCoursePo> publicCoursePos = publicCourseMapper
          .selectByCoachId(coachId);
      for (PublicCoursePo publicCoursePo : publicCoursePos) {
        if (courseDate != null && !DateUtils
            .sameDate(publicCoursePo.getCourseDate(), courseDate)) {
          continue;
        }
        PublicCourseListItemDto publicCourseListItemDto = new PublicCourseListItemDto();
        BeanUtils.copyProperties(publicCoursePo, publicCourseListItemDto);

        final BusinessPo businessPo = businessMapper.selectByPrimaryKey(publicCoursePo.getBusinessId());
        if (businessPo.getPicId() != null) {
          final PicturePo picturePo = pictureMapper.selectByPrimaryKey(businessPo.getPicId());
          publicCourseListItemDto.setPicId(picturePo.getPicLink());
        } else {
          publicCourseListItemDto.setPicId(null);
        }

        publicCourseListItemDtoList.add(publicCourseListItemDto);
      }
      res = BaseResult.success("查询教练响应成功的团课列表成功");
      res.setData(publicCourseListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询教练响应成功的团课列表失败");
    }

    return res;
  }

  @Override
  public BaseResult listResponsesByCoachId(Integer coachId) {
    //查看教练响应的团课列表（我响应的团课）
    BaseResult res = null;

    try {
      List<PublicCourseResponseListItemDto> publicCourseResponseDtoList = new ArrayList<>();
      final List<ResponsesPublicPo> responsesPublicPos = responsesPublicMapper.selectByCoachId(coachId);
      for (ResponsesPublicPo responsesPublicPo : responsesPublicPos) {
        PublicCourseResponseListItemDto publicCourseResponseListItemDto = new PublicCourseResponseListItemDto();
        BeanUtils.copyProperties(responsesPublicPo, publicCourseResponseListItemDto);
        final PublicCoursePo publicCoursePo = publicCourseMapper
            .selectByPrimaryKey(publicCourseResponseListItemDto.getCourseId());

        publicCourseResponseListItemDto.setCourseDate(publicCoursePo.getCourseDate());
        publicCourseResponseListItemDto.setCourseName(publicCoursePo.getName());
        publicCourseResponseListItemDto.setCoursePrice(publicCoursePo.getPrice());
        publicCourseResponseListItemDto.setCoachPrice(responsesPublicPo.getPrice());

        publicCourseResponseDtoList.add(publicCourseResponseListItemDto);
      }
      res = BaseResult.success("查看教练响应的团课列表成功");
      res.setData(publicCourseResponseDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查看教练响应的团课列表失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult changeResponsePrice(ResponseToPublicCourseDto responseToPublicCourseDto) {
    //教练修改团课的响应价格
    BaseResult res = null;

    try {
      final Integer coachId = responseToPublicCourseDto.getCoachId();
      final Integer courseId = responseToPublicCourseDto.getCourseId();

      ResponsesPublicPoKey responsesPublicPoKey = new ResponsesPublicPoKey();
      BeanUtils.copyProperties(responseToPublicCourseDto, responsesPublicPoKey);
      ResponsesPublicPo responsesPublicPo = responsesPublicMapper
          .selectByPrimaryKey(responsesPublicPoKey);

      if (responsesPublicPo.getStatus().equals(ResponseStatus.ACCEPTED)) {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "不能更改已经被接受的响应");
        return res;
      }

      responsesPublicPo.setPrice(responseToPublicCourseDto.getPrice());
      responsesPublicPo.setStatus(ResponseStatus.NEW_PUBLISH);

      responsesPublicMapper.updateByPrimaryKey(responsesPublicPo);
      res = BaseResult.success("教练修改价格成功");
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("教练修改价格失败");
    }

    return res;
  }

  @Override
  public BaseResult listFinishedPublicCourses(Integer coachId) {
    //查看教练 结课团课列表
    BaseResult res = null;

    try {
      final List<PublicCoursePo> publicCourses = publicCourseMapper.selectFinishedPublicCoursesByCoachId(coachId);
      List<PublicCourseListItemDto> publicCourseListItemDtoList = new ArrayList<>();
      for (PublicCoursePo publicCoursePo : publicCourses) {
        PublicCourseListItemDto publicCourseListItemDto = new PublicCourseListItemDto();
        BeanUtils.copyProperties(publicCoursePo, publicCourseListItemDto);
        publicCourseListItemDtoList.add(publicCourseListItemDto);
      }
      res = BaseResult.success("查询查看教练结课团课列表成功");
      res.setData(publicCourseListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询查看教练结课团课列表失败");
    }
    return res;
  }

  @Override
  public BaseResult listRequiredPublicCourses(Integer coachId) {
    //查看教练待上团课列表
    BaseResult res = null;
    try {
      final List<PublicCoursePo> publicCourses = publicCourseMapper.selectRequiredPublicCoursesByCoachId(coachId);
      List<PublicCourseListItemDto> publicCourseListItemDtoList = new ArrayList<>();
      for (PublicCoursePo publicCoursePo : publicCourses) {
        PublicCourseListItemDto publicCourseListItemDto = new PublicCourseListItemDto();
        BeanUtils.copyProperties(publicCoursePo, publicCourseListItemDto);
        publicCourseListItemDtoList.add(publicCourseListItemDto);
      }
      res = BaseResult.success("查询查看教练待上团课列表成功");
      res.setData(publicCourseListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询查看教练待上团课列表失败");
    }
    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult finishPublicCourseByCourseId(Integer courseId) {
    //团课结课
    BaseResult res = null;
    try {
      final PublicCoursePo publicCoursePo = publicCourseMapper.selectByPrimaryKey(courseId);
      ReceiveRecordPo receiveRecordCoachFeePo = new ReceiveRecordPo();
      ReceiveRecordPo receiveRecordBusinessFeePo = new ReceiveRecordPo();
      final Integer status = publicCoursePo.getStatus();
      if (PublicCourseStatus.NEW_PUBLISH.equals(status) ||
          status.equals(PublicCourseStatus.COACH_SELECTED)||
          status.equals(PublicCourseStatus.CUSTOMER_PAID) ||
          status.equals(PublicCourseStatus.FULL) ) {

        publicCourseMapper.finishByPrimaryKey(courseId);
        //教练费（fromId：商家、toId：教练）
        receiveRecordCoachFeePo.setFromId(publicCoursePo.getBusinessId());
        receiveRecordCoachFeePo.setToId(publicCoursePo.getCoachId());
        receiveRecordCoachFeePo.setAmount(publicCoursePo.getCoachPrice());
        receiveRecordCoachFeePo.setType(3);//教练费
        receiveRecordCoachFeePo.setCreatedTime(new Date());
        receiveRecordMapper.insertWithoutId(receiveRecordCoachFeePo);

        //团课课程费（fromId：很多顾客、toId: 商家）
        List<TakesPublicPoKey> takesPublicPoKeyList = takesPublicMapper.selectByCourseId(courseId);
        for(TakesPublicPoKey takesPublicPoKey:takesPublicPoKeyList){
          receiveRecordBusinessFeePo.setFromId(takesPublicPoKey.getCustomerId());
          receiveRecordBusinessFeePo.setToId(publicCoursePo.getBusinessId());
          receiveRecordBusinessFeePo.setAmount(publicCoursePo.getPrice());
          receiveRecordBusinessFeePo.setType(1);//团课课程费
          receiveRecordBusinessFeePo.setCreatedTime(new Date());
          receiveRecordMapper.insertWithoutId(receiveRecordBusinessFeePo);
        }
        res = BaseResult.success("团课结课成功，教练费 & 团课课程费 结算成功");

      } else {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "该课程已结束");
      }
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("团课结课失败");
    }
    return res;
  }



}
