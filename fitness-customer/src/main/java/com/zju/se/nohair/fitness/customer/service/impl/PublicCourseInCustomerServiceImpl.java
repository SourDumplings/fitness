package com.zju.se.nohair.fitness.customer.service.impl;

import com.zju.se.nohair.fitness.commons.constant.PublicCourseStatus;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.customer.dao.mapper.PublicCourseInCustomerMapper;
import com.zju.se.nohair.fitness.customer.dto.CoachDetailsForCustomerDto;
import com.zju.se.nohair.fitness.customer.dto.GymDetailsForCustomerDto;
import com.zju.se.nohair.fitness.customer.dto.PublicCourseDetailsForCustomerDto;
import com.zju.se.nohair.fitness.customer.dto.PublicCourseItemOfListDto;
import com.zju.se.nohair.fitness.customer.dto.SelectPublicCourseDto;
import com.zju.se.nohair.fitness.customer.service.PublicCourseInCustomerService;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.mapper.GymMapper;
import com.zju.se.nohair.fitness.dao.mapper.OwnsGymMapper;
import com.zju.se.nohair.fitness.dao.mapper.PrivateCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.PrivateTimeSlotMapper;
import com.zju.se.nohair.fitness.dao.mapper.PublicCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.PublicOrderMapper;
import com.zju.se.nohair.fitness.dao.mapper.RatesMapper;
import com.zju.se.nohair.fitness.dao.mapper.TakesPublicMapper;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.dao.po.CustomerPo;
import com.zju.se.nohair.fitness.dao.po.GymPo;
import com.zju.se.nohair.fitness.dao.po.OwnsGymPoKey;
import com.zju.se.nohair.fitness.dao.po.PrivateCoursePo;
import com.zju.se.nohair.fitness.dao.po.PublicCoursePo;
import com.zju.se.nohair.fitness.dao.po.PublicOrderPo;
import com.zju.se.nohair.fitness.dao.po.RatesPo;
import com.zju.se.nohair.fitness.dao.po.TakesPrivatePo;
import com.zju.se.nohair.fitness.dao.po.TakesPrivatePoKey;
import com.zju.se.nohair.fitness.dao.po.TakesPublicPoKey;
import com.zju.se.nohair.fitness.dao.po.VipCardPo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 团课相关操作 service 实现.
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/15 18:06
 */
@Service
public class PublicCourseInCustomerServiceImpl implements PublicCourseInCustomerService {
  private static Logger logger = LoggerFactory.getLogger(PublicCourseInCustomerServiceImpl.class);

  @Autowired
  private PublicCourseMapper publicCourseMapper;
  @Autowired
  private TakesPublicMapper takesPublicMapper;
  @Autowired
  private PublicOrderMapper publicOrderMapper;
  @Autowired
  private GymMapper gymMapper;
  @Autowired
  private PublicCourseInCustomerMapper publicCourseInCustomerMapper;
  @Autowired
  private OwnsGymMapper ownsGymMapper;
  @Autowired
  private RatesMapper ratesMapper;
  @Autowired
  private CoachMapper coachMapper;
  @Autowired
  private CustomerMapper customerMapper;
  @Autowired
  private PrivateTimeSlotMapper privateTimeSlotMapper;

  @Override
  public BaseResult selectPublicCourse(Integer courseId, Integer customerId) {
    BaseResult res = null;

    try {

      TakesPublicPoKey takesPublicPoKey = new TakesPublicPoKey();
      takesPublicPoKey.setCourseId(courseId);
      takesPublicPoKey.setCustomerId(customerId);
      //得到是否已经选课 num=0代表没有选
      int num = takesPublicMapper.selectRecordExistOrNot(takesPublicPoKey);

      if (num==0){
        //查询团课状态 并根据状态判断是否改变团课状态
        PublicCoursePo publicCoursePo = publicCourseMapper.selectByPrimaryKey(courseId);
        int status = publicCoursePo.getStatus();
        if (status == PublicCourseStatus.COACH_SELECTED){
          PublicCoursePo temp = new PublicCoursePo();
          temp.setId(courseId);
          temp.setStatus(PublicCourseStatus.CUSTOMER_PAID);
          publicCourseMapper.updateByPrimaryKeySelective(temp);
        }else if (status == PublicCourseStatus.CUSTOMER_PAID){
          int peopleCount = publicOrderMapper.getChosenCount(courseId);
          if (peopleCount>=publicCoursePo.getCapacity()){
            res = BaseResult.fail("人数已满");
            return res;
          }else if (peopleCount+1==publicCoursePo.getCapacity()){
            PublicCoursePo temp = new PublicCoursePo();
            temp.setId(courseId);
            temp.setStatus(PublicCourseStatus.FULL);
            publicCourseMapper.updateByPrimaryKeySelective(temp);
          }
        }

        PublicOrderPo publicOrderPo = new PublicOrderPo();
        publicOrderPo.setCreatedTime(new Date());
        publicOrderPo.setStatus(2);
        publicOrderPo.setCourseId(courseId);
        publicOrderPo.setCustomerId(customerId);
        publicOrderPo.setOrderPrice(publicCoursePo.getPrice());

        customerMapper.reduceBalance(customerId,publicCoursePo.getPrice());

        publicOrderMapper.insert(publicOrderPo);

        takesPublicMapper.insert(takesPublicPoKey);
        res = BaseResult.success("选课成功");
      }else {
        res = BaseResult.fail("已经选过该课");
      }


    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("选课失败");
    }

    return res;
  }

  @Override
  public BaseResult getPublicCourseList() {
    BaseResult res = null;

    try {
     List<PublicCourseItemOfListDto> listDtos = publicCourseInCustomerMapper.getListForCustomerWithGymInfo();

      res = BaseResult.success("客户查询团课列表成功");
      res.setData(listDtos);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("客户查询团课列表失败");
    }

    return res;
  }

  @Override
  public BaseResult getPublicCourseDetail(Integer courseId, Integer customerId) {
    BaseResult res = null;

    try {
      PublicCoursePo publicCoursePo = publicCourseMapper.selectByPrimaryKey(courseId);
      PublicCourseDetailsForCustomerDto publicCourseDetailsForCustomerDto = new PublicCourseDetailsForCustomerDto();
      BeanUtils.copyProperties(publicCoursePo,publicCourseDetailsForCustomerDto);

      int peopleCount = publicOrderMapper.getChosenCount(courseId);
      publicCourseDetailsForCustomerDto.setChosenCount(peopleCount);

      OwnsGymPoKey ownsGymPoKey = ownsGymMapper.selectByBusinessId(publicCoursePo.getBusinessId());

      GymPo gymPo = gymMapper.selectByPrimaryKey(ownsGymPoKey.getGymId());
      GymDetailsForCustomerDto gymDetailsForCustomerDto = new GymDetailsForCustomerDto();
      BeanUtils.copyProperties(gymPo, gymDetailsForCustomerDto);

      List<RatesPo> ratesPoList = ratesMapper.selectByGymId(ownsGymPoKey.getGymId());
      double avg = 0;
      for(RatesPo r : ratesPoList){
        avg += r.getRatingPoints();
      }
      avg = ratesPoList.size()==0?0:avg/ratesPoList.size();
      gymDetailsForCustomerDto.setAvgRating(avg);

      publicCourseDetailsForCustomerDto.setGymDetail(gymDetailsForCustomerDto);

      CoachPo coachPo = coachMapper.selectByPrimaryKey(publicCoursePo.getCoachId());
      CoachDetailsForCustomerDto coachDetailsForCustomerDto = new CoachDetailsForCustomerDto();
      BeanUtils.copyProperties(coachPo, coachDetailsForCustomerDto);

      List<RatesPo> ratesList = ratesMapper.selectByCoachId(publicCoursePo.getCoachId());
      avg = 0;
      for(RatesPo r : ratesList){
        avg += r.getRatingPoints();
      }
      avg = ratesList.size()==0?0:avg/ratesList.size();
      coachDetailsForCustomerDto.setAvgRating(avg);

      publicCourseDetailsForCustomerDto.setCoachDetail(coachDetailsForCustomerDto);

      res = BaseResult.success("查询详情成功");
      res.setData(publicCourseDetailsForCustomerDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询详情失败");
    }

    return res;
  }

  @Override
  public BaseResult getGymNameList() {
    BaseResult res = null;

    try {
      final List<String> gymNameList = gymMapper
          .getGymNameListHasPublicCourse();

      res = BaseResult.success("查询开设团课的场馆name列表成功");
      res.setData(gymNameList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询开设团课的场馆name列表失败");
    }

    return res;
  }

  @Override
  public BaseResult checkCustomerChosenPublicOrNot(Integer courseId, Integer customerId) {
    BaseResult res = null;

    try {
      TakesPublicPoKey takesPublicPoKey = new TakesPublicPoKey();
      takesPublicPoKey.setCourseId(courseId);
      takesPublicPoKey.setCustomerId(customerId);
      //得到是否已经选课 num=0代表没有选
      int num = takesPublicMapper.selectRecordExistOrNot(takesPublicPoKey);

      res = BaseResult.success("查询成功");
      if (num==0){
        res.setMessage("false");
      }else {
        res.setMessage("true");
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询失败");
    }

    return res;
  }

  @Override
  public BaseResult cancelPublicCourse(Integer courseId, Integer customerId) {
    BaseResult res = null;

    try {
      TakesPublicPoKey takesPublicPoKey = new TakesPublicPoKey();
      takesPublicPoKey.setCourseId(courseId);
      takesPublicPoKey.setCustomerId(customerId);
      //得到是否已经选课 num=0代表没有选
      int num = takesPublicMapper.selectRecordExistOrNot(takesPublicPoKey);

      if (num==1){
        takesPublicMapper.deleteByPrimaryKey(takesPublicPoKey);
        PublicOrderPo publicOrderPo = new PublicOrderPo();
        publicOrderPo.setCustomerId(customerId);
        publicOrderPo.setCourseId(courseId);
        publicOrderMapper.updateToCancelStatus(publicOrderPo);

        PublicCoursePo publicCoursePo = publicCourseMapper.selectByPrimaryKey(courseId);
        int peopleCount = publicOrderMapper.getChosenCount(courseId);
        if (peopleCount>=publicCoursePo.getCapacity()){
          PublicCoursePo temp = new PublicCoursePo();
          temp.setId(courseId);
          temp.setStatus(PublicCourseStatus.CUSTOMER_PAID);
          publicCourseMapper.updateByPrimaryKeySelective(temp);
        }
        customerMapper.addBalance(customerId,publicCoursePo.getPrice());
      }
      res = BaseResult.success("取消课程成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("取消课程失败");
    }

    return res;
  }

  @Override
  public BaseResult getPublicCourseListForCustomerExceptCommented(Integer customerId) {
    BaseResult res = null;

    try {
      List<PublicCourseItemOfListDto> listDtos = publicCourseInCustomerMapper.getListForCustomerExceptCommented(customerId);

      res = BaseResult.success("客户查询团课列表成功");
      res.setData(listDtos);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("客户查询团课列表失败");
    }

    return res;
  }

}
