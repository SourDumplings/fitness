package com.zju.se.nohair.fitness.customer.service.impl;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.customer.dto.CoachDetailsForCustomerDto;
import com.zju.se.nohair.fitness.customer.dto.GymDetailsForCustomerDto;
import com.zju.se.nohair.fitness.customer.service.GymInCustomerService;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.GymMapper;
import com.zju.se.nohair.fitness.dao.mapper.RatesMapper;
import com.zju.se.nohair.fitness.dao.mapper.VipCardMapper;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.dao.po.GymPo;
import com.zju.se.nohair.fitness.dao.po.RatesPo;
import com.zju.se.nohair.fitness.dao.po.VipCardPo;
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
 * @date 2019/12/23 16:11
 */
@Service
public class GymInCustomerServiceImp implements GymInCustomerService {
  private static Logger logger = LoggerFactory.getLogger(CoachInCustomerServiceImp.class);

  @Autowired
  private GymMapper gymMapper;
  @Autowired
  private VipCardMapper vipCardMapper;
  @Autowired
  private RatesMapper ratesMapper;

  @Override
  public BaseResult getGymDetail(Integer gymId) {
    BaseResult res = null;

    try {
      GymPo gymPo = gymMapper.selectByPrimaryKey(gymId);
      GymDetailsForCustomerDto gymDetailsForCustomerDto = new GymDetailsForCustomerDto();
      BeanUtils.copyProperties(gymPo, gymDetailsForCustomerDto);

      List<RatesPo> ratesPoList = ratesMapper.selectByGymId(gymId);
      double avg = 0;
      for(RatesPo r : ratesPoList){
        avg += r.getRatingPoints();
      }
      avg = ratesPoList.size()==0?0:avg/ratesPoList.size();
      gymDetailsForCustomerDto.setAvgRating(avg);
      gymDetailsForCustomerDto.setRatesList(ratesPoList);

      List<VipCardPo> vipCardPoList = vipCardMapper.selectByGymId(gymId);
      gymDetailsForCustomerDto.setVipCardList(vipCardPoList);

      res = BaseResult.success("查询详情成功");
      res.setData(gymDetailsForCustomerDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询详情失败");
    }

    return res;
  }
}
