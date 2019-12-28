package com.zju.se.nohair.fitness.customer.service.impl;

import com.zju.se.nohair.fitness.commons.constant.PrivateCourseStatus;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.customer.service.PrivateCourseInCustomerService;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.mapper.GymMapper;
import com.zju.se.nohair.fitness.dao.mapper.OwnsGymMapper;
import com.zju.se.nohair.fitness.dao.mapper.PrivateCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.PrivateOrderMapper;
import com.zju.se.nohair.fitness.dao.mapper.RatesMapper;
import com.zju.se.nohair.fitness.dao.mapper.TakesPrivateMapper;
import com.zju.se.nohair.fitness.dao.po.PrivateCoursePo;
import com.zju.se.nohair.fitness.dao.po.PrivateOrderPo;
import com.zju.se.nohair.fitness.dao.po.TakesPrivatePoKey;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  @Override

  public BaseResult selectPrivateCourse(Integer courseId, Integer customerId) {
    return null;
  }

  @Override
  public BaseResult getPrivateCourseList() {
    return null;
  }

  @Override
  public BaseResult getPrivateCourseDetail(Integer courseId, Integer customerId) {
    return null;
  }

  @Override
  public BaseResult getGymNameList() {
    return null;
  }

  @Override
  public BaseResult checkCustomerChosenPrivateOrNot(Integer courseId, Integer customerId) {
    return null;
  }

  @Override
  public BaseResult cancelPrivateCourse(Integer courseId, Integer customerId) {
    return null;
  }
}
