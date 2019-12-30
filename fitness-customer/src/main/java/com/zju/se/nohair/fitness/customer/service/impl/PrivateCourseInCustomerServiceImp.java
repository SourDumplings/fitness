package com.zju.se.nohair.fitness.customer.service.impl;

import com.zju.se.nohair.fitness.commons.constant.PrivateCourseStatus;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.customer.dao.mapper.PrivateCourseInCustomerMapper;
import com.zju.se.nohair.fitness.customer.dto.CoachDetailsForCustomerDto;
import com.zju.se.nohair.fitness.customer.dto.GymDetailsForCustomerDto;
import com.zju.se.nohair.fitness.customer.dto.PrivateCourseDetailsForCustomerDto;
import com.zju.se.nohair.fitness.customer.dto.PrivateCourseItemOfListDto;
import com.zju.se.nohair.fitness.customer.service.PrivateCourseInCustomerService;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.mapper.GymMapper;
import com.zju.se.nohair.fitness.dao.mapper.OwnsGymMapper;
import com.zju.se.nohair.fitness.dao.mapper.PrivateCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.PrivateOrderMapper;
import com.zju.se.nohair.fitness.dao.mapper.RatesMapper;
import com.zju.se.nohair.fitness.dao.mapper.TakesPrivateMapper;
import com.zju.se.nohair.fitness.dao.mapper.TimeSlotMapper;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.dao.po.GymPo;
import com.zju.se.nohair.fitness.dao.po.OwnsGymPoKey;
import com.zju.se.nohair.fitness.dao.po.PrivateCoursePo;
import com.zju.se.nohair.fitness.dao.po.PrivateOrderPo;
import com.zju.se.nohair.fitness.dao.po.RatesPo;
import com.zju.se.nohair.fitness.dao.po.TakesPrivatePo;
import com.zju.se.nohair.fitness.dao.po.TakesPrivatePoKey;
import com.zju.se.nohair.fitness.dao.po.TimeSlotPo;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/28 18:53
 */
@Service
public class PrivateCourseInCustomerServiceImp implements PrivateCourseInCustomerService {

  private static Logger logger = LoggerFactory.getLogger(PrivateCourseInCustomerServiceImp.class);

  @Autowired
  private PrivateCourseMapper privateCourseMapper;
  @Autowired
  private TakesPrivateMapper takesPrivateMapper;
  @Autowired
  private PrivateOrderMapper privateOrderMapper;
  @Autowired
  private GymMapper gymMapper;
  @Autowired
  private OwnsGymMapper ownsGymMapper;
  @Autowired
  private RatesMapper ratesMapper;
  @Autowired
  private CoachMapper coachMapper;
  @Autowired
  private CustomerMapper customerMapper;
  @Autowired
  private PrivateCourseInCustomerMapper privateCourseInCustomerMapper;
  @Autowired
  private TimeSlotMapper timeSlotMapper;

  @Override
  public BaseResult selectPrivateCourse(Integer courseId, Integer customerId,Integer timeSlotId) {
    BaseResult res = null;

    try {

      TakesPrivatePo takesPrivatePo = new TakesPrivatePo();
      takesPrivatePo.setCourseId(courseId);
      takesPrivatePo.setCustomerId(customerId);
      takesPrivatePo.setTimeSlotId(timeSlotId);
      //得到是否已经选课 num=0代表没有选
      int num = takesPrivateMapper.selectRecordExistOrNot(takesPrivatePo);

      if (num==0){
        //查询私教课状态 并根据状态判断是否改变课的状态
        PrivateCoursePo privateCoursePo = privateCourseMapper.selectByPrimaryKey(courseId);
        int status = privateCoursePo.getStatus();
        if (status == PrivateCourseStatus.BUSINESS_SELECTED){
          PrivateCoursePo temp = new PrivateCoursePo();
          temp.setId(courseId);
          temp.setStatus(PrivateCourseStatus.CUSTOMER_PAID);
          privateCourseMapper.updateByPrimaryKeySelective(temp);
        }else if (status == PrivateCourseStatus.CUSTOMER_PAID){
          //是否已经被其他人选了
          TakesPrivatePo temp = new TakesPrivatePo();
          temp.setCustomerId(customerId);
          temp.setTimeSlotId(timeSlotId);
          int count = takesPrivateMapper.selectRecordExistOrNot(temp);
          if (count!=0){
            res = BaseResult.fail("已经有人选过该课");
            return res;
          }
        }

        PrivateOrderPo privateOrderPo = new PrivateOrderPo();
        privateOrderPo.setCreatedTime(new Date());
        privateOrderPo.setStatus(2);
        privateOrderPo.setCourseId(courseId);
        privateOrderPo.setCustomerId(customerId);
        privateOrderPo.setOrderPrice(privateCoursePo.getPrice());

        customerMapper.reduceBalance(customerId,privateCoursePo.getPrice());

        privateOrderMapper.insert(privateOrderPo);

        takesPrivateMapper.insert(takesPrivatePo);
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
  public BaseResult getPrivateCourseList() {
    BaseResult res = null;

    try {
      List<PrivateCourseItemOfListDto> listDtos = privateCourseInCustomerMapper.getListForCustomerWithGymInfo();

      res = BaseResult.success("客户查询私课列表成功");
      res.setData(listDtos);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("客户查询私课列表失败");
    }

    return res;
  }

  @Override
  public BaseResult getPrivateCourseDetail(Integer courseId, Integer customerId) {
    BaseResult res = null;

    try {
      PrivateCoursePo privateCoursePo = privateCourseMapper.selectByPrimaryKey(courseId);
      PrivateCourseDetailsForCustomerDto privateCourseDetailsForCustomerDto = new PrivateCourseDetailsForCustomerDto();
      BeanUtils.copyProperties(privateCoursePo,privateCourseDetailsForCustomerDto);

      List<TimeSlotPo> timeSlotPos = timeSlotMapper.getPrivateCourseBookableTimeSlots(courseId);
      privateCourseDetailsForCustomerDto.setTimeSlots(timeSlotPos);

      OwnsGymPoKey ownsGymPoKey = ownsGymMapper.selectByBusinessId(privateCoursePo.getBusinessId());

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

      privateCourseDetailsForCustomerDto.setGymDetail(gymDetailsForCustomerDto);

      CoachPo coachPo = coachMapper.selectByPrimaryKey(privateCoursePo.getCoachId());
      CoachDetailsForCustomerDto coachDetailsForCustomerDto = new CoachDetailsForCustomerDto();
      BeanUtils.copyProperties(coachPo, coachDetailsForCustomerDto);

      List<RatesPo> ratesList = ratesMapper.selectByCoachId(privateCoursePo.getCoachId());
      avg = 0;
      for(RatesPo r : ratesList){
        avg += r.getRatingPoints();
      }
      avg = ratesList.size()==0?0:avg/ratesList.size();
      coachDetailsForCustomerDto.setAvgRating(avg);

      privateCourseDetailsForCustomerDto.setCoachDetail(coachDetailsForCustomerDto);

      TakesPrivatePo takesPrivatePo = new TakesPrivatePo();
      takesPrivatePo.setCourseId(courseId);
      takesPrivatePo.setCustomerId(customerId);
      int count = takesPrivateMapper.selectRecordExistOrNot(takesPrivatePo);
      if (count == 0){
        privateCourseDetailsForCustomerDto.setChosenOrNot(false);
      }else {
        privateCourseDetailsForCustomerDto.setChosenOrNot(true);
        List<TimeSlotPo> temps = timeSlotMapper.getPrivateCustomerChosenTimeSlots(courseId,customerId);
        privateCourseDetailsForCustomerDto.setBuyTimeSlots(temps);
      }

      res = BaseResult.success("查询详情成功");
      res.setData(privateCourseDetailsForCustomerDto);
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
          .getGymNameListHasPrivateCourse();

      res = BaseResult.success("查询开设团课的场馆name列表成功");
      res.setData(gymNameList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询开设团课的场馆name列表失败");
    }

    return res;
  }

  @Override
  public BaseResult checkCustomerChosenPrivateOrNot(Integer courseId, Integer customerId, Integer timeSlotId) {
    BaseResult res = null;

    try {
      TakesPrivatePo takesPrivatePo = new TakesPrivatePo();
      takesPrivatePo.setCourseId(courseId);
      takesPrivatePo.setCustomerId(customerId);
      takesPrivatePo.setTimeSlotId(timeSlotId);
      //得到是否已经选课 num=0代表没有选
      int num = takesPrivateMapper.selectRecordExistOrNot(takesPrivatePo);

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
  public BaseResult cancelPrivateCourse(Integer courseId, Integer customerId, Integer timeSlotId) {
    BaseResult res = null;

    try {
      TakesPrivatePo takesPrivatePo = new TakesPrivatePo();
      takesPrivatePo.setCourseId(courseId);
      takesPrivatePo.setCustomerId(customerId);
      takesPrivatePo.setTimeSlotId(timeSlotId);
      //得到是否已经选课 num=0代表没有选
      int num = takesPrivateMapper.selectRecordExistOrNot(takesPrivatePo);

      if (num==1){
        takesPrivateMapper.deleteByPo(takesPrivatePo);
        PrivateOrderPo privateOrderPo = new PrivateOrderPo();
        privateOrderPo.setCustomerId(customerId);
        privateOrderPo.setCourseId(courseId);
        privateOrderMapper.updateToCancelStatus(privateOrderPo);

        PrivateCoursePo privateCoursePo = privateCourseMapper.selectByPrimaryKey(courseId);

        customerMapper.addBalance(customerId,privateCoursePo.getPrice());
      }
      res = BaseResult.success("取消课程成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("取消课程失败");
    }

    return res;
  }
}
