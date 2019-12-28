package com.zju.se.nohair.fitness.business.service.impl;

import com.zju.se.nohair.fitness.business.service.VipCardService;
import com.zju.se.nohair.fitness.business.dto.BusinessVipCardListItemDto;
import com.zju.se.nohair.fitness.business.dto.CreateVipCardDto;
import com.zju.se.nohair.fitness.dao.po.GymPo;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.GymMapper;
import com.zju.se.nohair.fitness.dao.mapper.GymVipCardMapper;
import com.zju.se.nohair.fitness.dao.mapper.OwnsGymMapper;
import com.zju.se.nohair.fitness.dao.po.GymVipCardPo;
import com.zju.se.nohair.fitness.dao.po.GymVipCardPoKey;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商家针对会员卡的服务实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/24 16:22
 */
@Service
public class VipCardServiceImpl implements VipCardService {

  private static Logger logger = LoggerFactory.getLogger(VipCardServiceImpl.class);

  private GymMapper gymMapper;

  private OwnsGymMapper ownsGymMapper;

  private GymVipCardMapper gymVipCardMapper;

  @Autowired
  public void setGymMapper(GymMapper gymMapper) {
    this.gymMapper = gymMapper;
  }

  @Autowired
  public void setGymVipCardMapper(GymVipCardMapper gymVipCardMapper) {
    this.gymVipCardMapper = gymVipCardMapper;
  }

  @Autowired
  public void setOwnsGymMapper(OwnsGymMapper ownsGymMapper) {
    this.ownsGymMapper = ownsGymMapper;
  }

  @Override
  public BaseResult listVipCardInfo(Integer businessId) {
    BaseResult res = null;

    try {
      final int gymId = ownsGymMapper.selectGymIdByBusinessId(businessId);
      final GymPo gymPo = gymMapper.selectByPrimaryKey(gymId);
      final String gymName = gymPo.getName();
      final List<GymVipCardPo> gymVipCardPos = gymVipCardMapper.selectAllByGymId(gymId);
      List<BusinessVipCardListItemDto> businessVipCardListItemDtos = new ArrayList<>();
      for (GymVipCardPo gymVipCardPo : gymVipCardPos) {
        BusinessVipCardListItemDto businessVipCardListItemDto = new BusinessVipCardListItemDto();
        businessVipCardListItemDto.setGymName(gymName);
        BeanUtils.copyProperties(gymVipCardPo, businessVipCardListItemDto);
        businessVipCardListItemDtos.add(businessVipCardListItemDto);
      }
      res = BaseResult.success("查询发布的会员卡信息成功");
      res.setData(businessVipCardListItemDtos);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询发布的会员卡信息失败");
    }

    return res;
  }

  @Override
  public BaseResult pushNewCardService(CreateVipCardDto createVipCardDto) {
    BaseResult res = null;

    try {
      final int gymId = ownsGymMapper.selectGymIdByBusinessId(createVipCardDto.getBusinessId());
      final Integer type = createVipCardDto.getType();

      GymVipCardPoKey gymVipCardPoKey = new GymVipCardPoKey();
      gymVipCardPoKey.setGymId(gymId);
      gymVipCardPoKey.setType(type);

      if (gymVipCardMapper.selectByPrimaryKey(gymVipCardPoKey) != null) {
        res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "不能重复添加会员卡");
      } else {
        GymVipCardPo gymVipCardPo = new GymVipCardPo();
        BeanUtils.copyProperties(gymVipCardPoKey, gymVipCardPo);
        gymVipCardPo.setPrice(createVipCardDto.getPrice());
        gymVipCardMapper.insert(gymVipCardPo);
      }

      res = BaseResult.success("发布会员卡信息成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("发布会员卡信息失败");
    }

    return res;
  }
}
