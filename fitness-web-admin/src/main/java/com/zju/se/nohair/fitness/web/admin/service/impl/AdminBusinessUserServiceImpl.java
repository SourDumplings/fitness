package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.commons.constant.CertificationStatus;
import com.zju.se.nohair.fitness.commons.constant.GenderTag;
import com.zju.se.nohair.fitness.commons.constant.ReceiveRecordType;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.commons.utils.DateUtils;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.mapper.GymMapper;
import com.zju.se.nohair.fitness.dao.mapper.OwnsGymMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.mapper.RatesMapper;
import com.zju.se.nohair.fitness.dao.mapper.ReceiveRecordMapper;
import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import com.zju.se.nohair.fitness.dao.po.OwnsGymPoKey;
import com.zju.se.nohair.fitness.dao.po.PicturePo;
import com.zju.se.nohair.fitness.dao.po.ReceiveRecordPo;
import com.zju.se.nohair.fitness.web.admin.dto.AdminBusinessUserDetailDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminBusinessUserListItemDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminBusinessUserReceiveRecordListItemDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCreateBusinessUserDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminBusinessUserService;
import com.zju.se.nohair.fitness.web.admin.utils.PicUtils;
import java.math.BigDecimal;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 后台模块的用户子模块下的商家用户 Service 接口实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/16 9:37
 */
@Transactional(readOnly = true)
@Service
public class AdminBusinessUserServiceImpl implements AdminBusinessUserService {

  private static Logger logger = LoggerFactory.getLogger(AdminBusinessUserServiceImpl.class);

  private BusinessMapper businessMapper;

  private PictureMapper pictureMapper;

  private OwnsGymMapper ownsGymMapper;

  private GymMapper gymMapper;

  private RatesMapper ratesMapper;

  private ReceiveRecordMapper receiveRecordMapper;

  private CustomerMapper customerMapper;

  @Autowired
  public void setBusinessMapper(BusinessMapper businessMapper) {
    this.businessMapper = businessMapper;
  }

  @Autowired
  public void setPictureMapper(PictureMapper pictureMapper) {
    this.pictureMapper = pictureMapper;
  }

  @Autowired
  public void setOwnsGymMapper(OwnsGymMapper ownsGymMapper) {
    this.ownsGymMapper = ownsGymMapper;
  }

  @Autowired
  public void setGymMapper(GymMapper gymMapper) {
    this.gymMapper = gymMapper;
  }

  @Autowired
  public void setRatesMapper(RatesMapper ratesMapper) {
    this.ratesMapper = ratesMapper;
  }

  @Autowired
  public void setReceiveRecordMapper(ReceiveRecordMapper receiveRecordMapper) {
    this.receiveRecordMapper = receiveRecordMapper;
  }

  @Autowired
  public void setCustomerMapper(CustomerMapper customerMapper) {
    this.customerMapper = customerMapper;
  }

  @Override
  public BaseResult listAll() {
    BaseResult res = null;

    try {
      final List<BusinessPo> businessPoList = businessMapper
          .selectAll();
      List<AdminBusinessUserListItemDto> adminBusinessUserListItemDtoList = new ArrayList<>();
      for (BusinessPo businessPo : businessPoList) {
        AdminBusinessUserListItemDto adminBusinessUserListItemDto = new AdminBusinessUserListItemDto();
        BeanUtils.copyProperties(businessPo, adminBusinessUserListItemDto);

        final OwnsGymPoKey ownsGymPoKey = ownsGymMapper.selectByBusinessId(businessPo.getId());
        if (ownsGymPoKey != null) {
          adminBusinessUserListItemDto
              .setGymName(gymMapper.selectByPrimaryKey(ownsGymPoKey.getGymId()).getName());
        }

        adminBusinessUserListItemDtoList.add(adminBusinessUserListItemDto);
      }
      res = BaseResult.success("查询商家列表成功");
      res.setData(adminBusinessUserListItemDtoList);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询商家列表失败");
    }

    return res;
  }

  @Override
  public BaseResult getDetailById(Integer id) {
    BaseResult res = null;

    try {
      AdminBusinessUserDetailDto adminBusinessUserDetailDto = new AdminBusinessUserDetailDto();
      final BusinessPo businessPo = businessMapper.selectByPrimaryKey(id);
      BeanUtils.copyProperties(businessPo, adminBusinessUserDetailDto);

      // 平均评分
      adminBusinessUserDetailDto.setRating(ratesMapper.countMeanRatingForBusinessUser(id));

      // 顾客性别统计，利用收入信息
      int[] genderDistribution = new int[2];
      genderDistribution[0] = genderDistribution[1] = 0;

      // 最近一周的输入统计
      BigDecimal[] weeklyIncomeDistribution = new BigDecimal[7];
      Arrays.fill(weeklyIncomeDistribution, BigDecimal.ZERO);

      // 收入信息统计
      Date nowDate = new Date();
      Date oneMonthBack = DateUtils.dateBack(nowDate, 0, 1, 0);
      Date oneWeekBack = DateUtils.dateBack(nowDate, 0, 0, 7);
      BigDecimal monthlyIncome = BigDecimal.ZERO;
      BigDecimal totalIncome = BigDecimal.ZERO;
      final List<ReceiveRecordPo> receiveRecordPos = receiveRecordMapper
          .selectAllBusinessIncomeRecordsByBusinessId(id);

      List<AdminBusinessUserReceiveRecordListItemDto> adminBusinessUserReceiveRecordListItemDtos = new ArrayList<>();

      for (ReceiveRecordPo receiveRecordPo : receiveRecordPos) {
        final BigDecimal amount = receiveRecordPo.getAmount();
        final Date createdTime = receiveRecordPo.getCreatedTime();
        final Integer type = receiveRecordPo.getType();

        // 收款记录清单
        AdminBusinessUserReceiveRecordListItemDto adminBusinessUserReceiveRecordListItemDto = new AdminBusinessUserReceiveRecordListItemDto();
        BeanUtils.copyProperties(receiveRecordPo, adminBusinessUserReceiveRecordListItemDto);

        // 统计周收入
        if (oneWeekBack.compareTo(createdTime) <= 0) {
          final Period dateDiff = DateUtils.dateDiff(createdTime, nowDate);
          weeklyIncomeDistribution[dateDiff.getDays()].add(amount);
        }

        // 统计月收入
        if (oneMonthBack.compareTo(createdTime) <= 0) {
          monthlyIncome = monthlyIncome.add(amount);
        }

        // 统计总收入
        totalIncome = totalIncome.add(amount);

        // 统计消费的顾客性别和收款方用户类型
        if (type.equals(ReceiveRecordType.PUBLIC_COURSE_FEE) ||
            type.equals(ReceiveRecordType.VIP_CARD_FEE)) {
          final Integer gender = customerMapper.selectByPrimaryKey(receiveRecordPo.getFromId())
              .getGender();
          if (gender.equals(GenderTag.MALE)) {
            ++genderDistribution[0];
          } else if (gender.equals(GenderTag.FEMALE)) {
            ++genderDistribution[1];
          }
          adminBusinessUserReceiveRecordListItemDto.setFromUserType("顾客");
        } else if (type.equals(ReceiveRecordType.GYM_FEE)) {
          adminBusinessUserReceiveRecordListItemDto.setFromUserType("教练");
        }

        adminBusinessUserReceiveRecordListItemDtos.add(adminBusinessUserReceiveRecordListItemDto);
      }
      adminBusinessUserDetailDto.setWeeklyIncomeDistribution(weeklyIncomeDistribution);
      adminBusinessUserDetailDto.setMonthlyIncome(monthlyIncome);
      adminBusinessUserDetailDto.setTotalIncome(totalIncome);

      adminBusinessUserDetailDto.setGenderDistribution(genderDistribution);
      adminBusinessUserDetailDto.setReceiveRecords(adminBusinessUserReceiveRecordListItemDtos);

      // 资格证
      final Integer certificationPicId = businessPo.getCertificationPicId();
      adminBusinessUserDetailDto.setCertificationPicLink(
          pictureMapper.selectByPrimaryKey(certificationPicId).getPicLink());

      // 健身房图片集
      final OwnsGymPoKey ownsGymPoKey = ownsGymMapper.selectByBusinessId(id);
      final Integer picGroupId = gymMapper.selectByPrimaryKey(ownsGymPoKey.getGymId())
          .getPicGroupId();
      if (picGroupId != null) {
        final List<PicturePo> picturePos = pictureMapper.selectByPicGroupId(picGroupId);
        List<String> gymPicLinks = new ArrayList<>();
        for (PicturePo picturePo : picturePos) {
          gymPicLinks.add(picturePo.getPicLink());
        }
        adminBusinessUserDetailDto.setGymPicLinks(gymPicLinks);
      }

      res = BaseResult.success("获取商家详情成功");
      res.setData(adminBusinessUserDetailDto);
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("获取商家详情失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult saveItem(AdminCreateBusinessUserDto adminCreateBusinessUserDto,
      MultipartFile profilePic, MultipartFile certificationPic) {
    BaseResult res = null;

    try {
      boolean isCreating = false;
      if (adminCreateBusinessUserDto.getId() == -1) {
        isCreating = true;
        adminCreateBusinessUserDto.setId(null);
      }

      BusinessPo businessPo = new BusinessPo();
      BeanUtils.copyProperties(adminCreateBusinessUserDto, businessPo);
      businessPo.setPassword(DigestUtils.md5DigestAsHex(businessPo.getPassword().getBytes()));
      businessPo.setStatus(CertificationStatus.NEW_PUBLISH);
      businessPo.setBalance(BigDecimal.ZERO);
      businessPo.setPicId(PicUtils.saveSinglePic(pictureMapper, profilePic));
      businessPo.setCertificationPicId(PicUtils.saveSinglePic(pictureMapper, certificationPic));

      if (isCreating) {
        businessPo.setCreatedTime(new Date());
        businessMapper.insert(businessPo);
        res = BaseResult.success("商家用户注册成功");
      } else {
        final BusinessPo target = businessMapper
            .selectByPrimaryKey(adminCreateBusinessUserDto.getId());
        if (target == null) {
          res = BaseResult.fail(BaseResult.STATUS_BAD_REQUEST, "无此 id 的商家用户");
        } else {
          businessPo.setCreatedTime(target.getCreatedTime());
          businessMapper.updateByPrimaryKey(businessPo);
          res = BaseResult.success("商家信息更新成功");
        }
      }

    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("商家用户注册/更新失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult deleteItem(Integer id) {
    BaseResult res = null;

    try {
      businessMapper.deleteByPrimaryKey(id);
      res = BaseResult.success("删除商家用户成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("删除商家用户失败");
    }

    return res;
  }
}
