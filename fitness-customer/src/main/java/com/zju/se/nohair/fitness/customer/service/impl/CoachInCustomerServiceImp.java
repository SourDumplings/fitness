package com.zju.se.nohair.fitness.customer.service.impl;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.customer.dto.CoachDetailsForCustomerDto;
import com.zju.se.nohair.fitness.customer.service.CoachInCustomerService;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.RatesMapper;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.dao.po.RatesPo;
import java.util.ArrayList;
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
 * @date 2019/12/23 14:20
 */

@Service
public class CoachInCustomerServiceImp implements CoachInCustomerService {
  private static Logger logger = LoggerFactory.getLogger(CoachInCustomerServiceImp.class);

  @Autowired
  private CoachMapper coachMapper;
  @Autowired
  private RatesMapper ratesMapper;

  @Override
  public BaseResult getCoachDetail(Integer coachId) {
    BaseResult res = null;

    try {
      CoachPo coachPo = coachMapper.selectByPrimaryKey(coachId);
      CoachDetailsForCustomerDto coachDetailsForCustomerDto = new CoachDetailsForCustomerDto();
      BeanUtils.copyProperties(coachPo, coachDetailsForCustomerDto);

      List<RatesPo> ratesPoList = ratesMapper.selectByCoachId(coachId);
      double avg = 0;
      for(RatesPo r : ratesPoList){
        avg += r.getRatingPoints();
      }
      avg = ratesPoList.size()==0?0:avg/ratesPoList.size();
      coachDetailsForCustomerDto.setAvgRating(avg);
      coachDetailsForCustomerDto.setRatesList(ratesPoList);

      res = BaseResult.success("查询详情成功");
      res.setData(coachDetailsForCustomerDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询详情失败");
    }

    return res;
  }
}
