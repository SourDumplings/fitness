package com.zju.se.nohair.fitness.coach.service.impl;

import com.zju.se.nohair.fitness.coach.dto.GymDto;
import com.zju.se.nohair.fitness.coach.service.GymService;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.GymMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.po.GymPo;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 健身房接口实现类
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-20
 */
@Service
public class GymServiceImpl  implements GymService {

  private static Logger logger = LoggerFactory.getLogger(GymServiceImpl.class);

  private GymMapper gymMapper;

  @Autowired
  public void setGymMapper(GymMapper gymMapper) {
    this.gymMapper = gymMapper;
  }

  @Override
  public BaseResult getGymDetail(Integer gymId) {
    //查看健身房详细信息
    BaseResult res = null;

    try {
      final GymPo gymPo = gymMapper.selectByPrimaryKey(gymId);
      GymDto gymDto = new GymDto();
      BeanUtils.copyProperties(gymPo, gymDto);

      res = BaseResult.success("查看健身房详细信息成功");
      res.setData(gymDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查看健身房详细信息失败");
    }

    return res;
  }

  @Override
  public BaseResult getGymDetailByBusinessId(Integer businessId) {
    //根据商家id查看健身房详细信息
    BaseResult res = null;

    try {
      final GymPo gymPo = gymMapper.selectByBusinessId(businessId);
      GymDto gymDto = new GymDto();
      BeanUtils.copyProperties(gymPo, gymDto);

      res = BaseResult.success("根据商家id查看健身房详细信息成功");
      res.setData(gymDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("根据商家id查看健身房详细信息失败");
    }

    return res;
  }

  @Override
  public BaseResult listGym() {
    //查看全部健身房列表
    BaseResult res = null;

    try {
      final List<GymPo> gyms = gymMapper.selectAllGyms();
      List<GymDto> gymDtoList = new ArrayList<>();
      for (GymPo gymPo : gyms) {
        GymDto gymDto = new GymDto();
        BeanUtils.copyProperties(gymPo, gymDto);
        gymDtoList.add(gymDto);
      }
      res = BaseResult.success("查看全部健身房列表成功");
      res.setData(gymDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查看全部健身房列表失败");
    }
    return res;
  }


}
