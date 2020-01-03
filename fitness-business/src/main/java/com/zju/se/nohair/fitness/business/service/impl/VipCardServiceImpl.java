package com.zju.se.nohair.fitness.business.service.impl;

import com.zju.se.nohair.fitness.business.dto.BusinessVipCardListItemDto;
import com.zju.se.nohair.fitness.business.dto.CreateVipCardDto;
import com.zju.se.nohair.fitness.business.service.VipCardService;
import com.zju.se.nohair.fitness.commons.constant.VipCardType;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.GymMapper;
import com.zju.se.nohair.fitness.dao.mapper.GymVipCardMapper;
import com.zju.se.nohair.fitness.dao.mapper.OwnsGymMapper;
import com.zju.se.nohair.fitness.dao.po.GymPo;
import com.zju.se.nohair.fitness.dao.po.GymVipCardPo;
import com.zju.se.nohair.fitness.dao.po.GymVipCardPoKey;
import com.zju.se.nohair.fitness.dao.po.OwnsGymPoKey;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 商家针对会员卡的服务实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/24 16:22
 */
@Transactional(readOnly = true)
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
      final OwnsGymPoKey ownsGymPoKey = ownsGymMapper.selectByBusinessId(businessId);

      if (ownsGymPoKey == null) {
        return BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "错误，无此商家！");
      }

      Integer gymId = ownsGymPoKey.getGymId();
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

  @Transactional(readOnly = false)
  @Override
  public BaseResult saveCardService(CreateVipCardDto createVipCardDto) {
    BaseResult res = null;

    try {
      final OwnsGymPoKey ownsGymPoKey = ownsGymMapper
          .selectByBusinessId(createVipCardDto.getBusinessId());

      if (ownsGymPoKey == null) {
        return BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "错误，无此商家！");
      }
      if (!createVipCardDto.getType().equals(VipCardType.YEARLY)
          && !createVipCardDto.getType().equals(VipCardType.MONTHLY)
          && !createVipCardDto.getType().equals(VipCardType.SESSIONLY)) {
        return BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "错误，无此类型！");
      }

      final Integer type = createVipCardDto.getType();

      GymVipCardPoKey gymVipCardPoKey = new GymVipCardPoKey();
      gymVipCardPoKey.setGymId(ownsGymPoKey.getGymId());
      gymVipCardPoKey.setType(type);

      GymVipCardPo gymVipCardPo = new GymVipCardPo();
      BeanUtils.copyProperties(gymVipCardPoKey, gymVipCardPo);
      gymVipCardPo.setPrice(createVipCardDto.getPrice());

      if (createVipCardDto.getPrice().equals(BigDecimal.valueOf(0))) {
        // 删除
        gymVipCardMapper.deleteByPrimaryKey(gymVipCardPoKey);
        res = BaseResult.success("删除会员卡信息成功");
      } else if (gymVipCardMapper.selectByPrimaryKey(gymVipCardPoKey) == null) {
        // 新增
        gymVipCardMapper.insert(gymVipCardPo);
        res = BaseResult.success("新增会员卡信息成功");
      } else {
        // 修改
        gymVipCardMapper.updateByPrimaryKey(gymVipCardPo);
        res = BaseResult.success("修改会员卡信息成功");
      }
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("保存会员卡信息失败");
    }

    return res;
  }
}
