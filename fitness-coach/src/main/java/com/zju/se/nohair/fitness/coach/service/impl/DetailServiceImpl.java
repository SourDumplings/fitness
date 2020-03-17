package com.zju.se.nohair.fitness.coach.service.impl;

import com.zju.se.nohair.fitness.coach.dto.CoachDetailDto;
import com.zju.se.nohair.fitness.coach.service.DetailService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 教练端-我的-接口实现类
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-17
 */
@Transactional(readOnly = true)
@Service
public class DetailServiceImpl implements DetailService {
  private static Logger logger = LoggerFactory.getLogger(FinanceServiceImpl.class);

  private CoachMapper coachMapper;

  @Autowired
  public void setCoachMapper(CoachMapper coachMapper) {
    this.coachMapper = coachMapper;
  }

  @Override
  public BaseResult getCoachDetailByCoachId(Integer coachId) {
    //查看教练的个人信息
    BaseResult res = null;

    try {
      final CoachPo coachPo = coachMapper.selectByPrimaryKey(coachId);
      CoachDetailDto coachDetailDto = new CoachDetailDto();
      BeanUtils.copyProperties(coachPo, coachDetailDto);
      res = BaseResult.success("查看教练的个人信息成功");
      res.setData(coachDetailDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查看教练的个人信息失败");
    }

    return res;
  }
}
