package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.commons.utils.DateUtils;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.mapper.GymMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.mapper.PrivateCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.RatesMapper;
import com.zju.se.nohair.fitness.dao.mapper.TakesPrivateMapper;
import com.zju.se.nohair.fitness.dao.mapper.TimeSlotMapper;
import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.dao.po.CustomerPo;
import com.zju.se.nohair.fitness.dao.po.GymPo;
import com.zju.se.nohair.fitness.dao.po.PicturePo;
import com.zju.se.nohair.fitness.dao.po.PrivateCoursePo;
import com.zju.se.nohair.fitness.dao.po.RatesPo;
import com.zju.se.nohair.fitness.dao.po.TakesPrivatePo;
import com.zju.se.nohair.fitness.dao.po.TakesPrivatePoKey;
import com.zju.se.nohair.fitness.dao.po.TimeSlotPo;
import com.zju.se.nohair.fitness.web.admin.dto.AdminPrivateCourseDetailDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminPrivateCourseListItemDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminPrivateCourseService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 后台模块的课程子模块下的私教课 Service 接口实现类.
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/2/6 20:21
 */
@Transactional(readOnly = true)
@Service
public class AdminPrivateCourseServiceImpl implements AdminPrivateCourseService {

  private static Logger logger = LoggerFactory.getLogger(AdminPrivateCourseServiceImpl.class);

  @Autowired
  private PrivateCourseMapper privateCourseMapper;

  @Autowired
  private CoachMapper coachMapper;

  @Autowired
  private BusinessMapper businessMapper;

  @Autowired
  private RatesMapper ratesMapper;

  @Autowired
  private GymMapper gymMapper;

  @Autowired
  private TakesPrivateMapper takesPrivateMapper;

  @Autowired
  private CustomerMapper customerMapper;

  @Autowired
  private PictureMapper pictureMapper;

  @Autowired
  private TimeSlotMapper timeSlotMapper;

  @Override
  public BaseResult listAll() {
    BaseResult res = null;

    try {
      final List<PrivateCoursePo> privateCoursePoList = privateCourseMapper.selectAll();
      final List<CoachPo> coachPoList = coachMapper.selectAll();
      final List<BusinessPo> businessPoList = businessMapper.selectAll();
      final List<GymPo> gymPoList = gymMapper.getNearGymList();
      final List<CustomerPo> customerPoList = customerMapper.selectAll();


      List<AdminPrivateCourseListItemDto> adminPrivateCourseListItemDtos = new ArrayList<>();
      for (PrivateCoursePo privateCoursePo : privateCoursePoList) {
        AdminPrivateCourseListItemDto adminPrivateCourseListItemDto = new AdminPrivateCourseListItemDto();
        adminPrivateCourseListItemDto.setCourseId(privateCoursePo.getId());
        adminPrivateCourseListItemDto.setCourseName(privateCoursePo.getName());
        for(CoachPo coachPo:coachPoList){
          if(coachPo.getId() == privateCoursePo.getCoachId()){
            adminPrivateCourseListItemDto.setCoachName(coachPo.getName());
            break;
          }
        }
        for (BusinessPo businessPo:businessPoList){
          if (businessPo.getId() == privateCoursePo.getBusinessId()){
            adminPrivateCourseListItemDto.setBusinessName(businessPo.getUsername());
            for(GymPo gymPo:gymPoList){
              if(gymPo.getBusinessId() == businessPo.getId()){
                adminPrivateCourseListItemDto.setGymName(gymPo.getName());
                break;
              }
            }
          }
          break;
        }


        if (adminPrivateCourseListItemDto.getGymName()==null || adminPrivateCourseListItemDto.getGymName().equals("")){
          adminPrivateCourseListItemDto.setGymName("暂无");
        }
        List<TakesPrivatePo> takesPrivatePoList = takesPrivateMapper.selectByCourseId(privateCoursePo.getId());
        for(TakesPrivatePo takesPrivatePo : takesPrivatePoList){
          AdminPrivateCourseListItemDto temp = new AdminPrivateCourseListItemDto();
          BeanUtils.copyProperties(adminPrivateCourseListItemDto,temp);
          for(CustomerPo customerPo : customerPoList){
            if (customerPo.getId() == takesPrivatePo.getCustomerId()){
              temp.setCustomerName(customerPo.getUsername());
              temp.setCustomerId(customerPo.getId());
              adminPrivateCourseListItemDtos.add(temp);
              break;
            }
          }

        }

      }
      res = BaseResult.success("查询私教课列表成功");
      res.setData(adminPrivateCourseListItemDtos);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询私教课列表失败");
    }

    return res;
  }

  @Override
  public BaseResult getDetailById(Integer id) {
    return null;
  }

  @Override
  public BaseResult getDetailByIds(Integer courseId,Integer customerId) {
    BaseResult res = null;

    try {

      PrivateCoursePo privateCoursePo = privateCourseMapper.selectByPrimaryKey(courseId);
      CoachPo coachPo = coachMapper.selectByPrimaryKey(privateCoursePo.getCoachId());
      BusinessPo businessPo = businessMapper.selectByPrimaryKey(privateCoursePo.getBusinessId());
      CustomerPo customerPo = customerMapper.selectByPrimaryKey(customerId);
      TakesPrivatePoKey takesPrivatePoKey = new TakesPrivatePoKey();
      takesPrivatePoKey.setCustomerId(customerId);
      takesPrivatePoKey.setCourseId(courseId);
      TakesPrivatePo takesPrivatePo = takesPrivateMapper.selectByPrimaryKey(takesPrivatePoKey);
      PicturePo picturePo = pictureMapper.selectByPrimaryKey(privateCoursePo.getPicId());
      TimeSlotPo timeSlotPo = timeSlotMapper.selectByPrimaryKey(takesPrivatePo.getTimeSlotId());

      AdminPrivateCourseDetailDto adminPrivateCourseDetailDto = new AdminPrivateCourseDetailDto();
      adminPrivateCourseDetailDto.setBusinessId(businessPo.getId());
      adminPrivateCourseDetailDto.setBusinessName(businessPo.getUsername());
      adminPrivateCourseDetailDto.setCoachId(coachPo.getId());
      adminPrivateCourseDetailDto.setCoachName(coachPo.getName());
      adminPrivateCourseDetailDto.setCourseContent(privateCoursePo.getContent());
      adminPrivateCourseDetailDto.setCourseId(privateCoursePo.getId());
      adminPrivateCourseDetailDto.setCourseName(privateCoursePo.getName());
      adminPrivateCourseDetailDto.setCoursePrice(privateCoursePo.getPrice().doubleValue());
      adminPrivateCourseDetailDto.setCustomerId(customerId);
      adminPrivateCourseDetailDto.setCustomerName(customerPo.getUsername());
      adminPrivateCourseDetailDto.setPicUrl(picturePo==null?"":picturePo.getPicLink());

      Date courseDate = privateCoursePo.getCourseDate();
      String courseTime = new SimpleDateFormat("yyyy/MM/dd").format(courseDate);
      String begin = new SimpleDateFormat("HH:mm").format(timeSlotPo.getBeginTime());
      String end = new SimpleDateFormat("HH:mm").format(timeSlotPo.getEndTime());
      courseTime = courseTime + " " + begin + " - " + end;
      adminPrivateCourseDetailDto.setCourseTime(courseTime);


      res = BaseResult.success("查询私教课详情成功");
      res.setData(adminPrivateCourseDetailDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询私教课详情失败");
    }

    return res;
  }

  @Override
  public BaseResult deleteItem(Integer id) {
    return null;
  }
}
