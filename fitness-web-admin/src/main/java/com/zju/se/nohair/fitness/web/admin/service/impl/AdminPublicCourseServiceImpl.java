package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.BusinessMapper;
import com.zju.se.nohair.fitness.dao.mapper.CoachMapper;
import com.zju.se.nohair.fitness.dao.mapper.CustomerMapper;
import com.zju.se.nohair.fitness.dao.mapper.GymMapper;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.mapper.PrivateCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.PublicCourseMapper;
import com.zju.se.nohair.fitness.dao.mapper.RatesMapper;
import com.zju.se.nohair.fitness.dao.mapper.TakesPrivateMapper;
import com.zju.se.nohair.fitness.dao.mapper.TakesPublicMapper;
import com.zju.se.nohair.fitness.dao.mapper.TimeSlotMapper;
import com.zju.se.nohair.fitness.dao.po.BusinessPo;
import com.zju.se.nohair.fitness.dao.po.CoachPo;
import com.zju.se.nohair.fitness.dao.po.CustomerPo;
import com.zju.se.nohair.fitness.dao.po.GymPo;
import com.zju.se.nohair.fitness.dao.po.PicturePo;
import com.zju.se.nohair.fitness.dao.po.PrivateCoursePo;
import com.zju.se.nohair.fitness.dao.po.PublicCoursePo;
import com.zju.se.nohair.fitness.dao.po.TakesPrivatePo;
import com.zju.se.nohair.fitness.dao.po.TakesPrivatePoKey;
import com.zju.se.nohair.fitness.dao.po.TakesPublicPoKey;
import com.zju.se.nohair.fitness.dao.po.TimeSlotPo;
import com.zju.se.nohair.fitness.web.admin.dto.AdminPrivateCourseDetailDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminPrivateCourseListItemDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminPublicCourseDetailDto;
import com.zju.se.nohair.fitness.web.admin.dto.AdminPublicCourseListItemDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminPublicCourseService;
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
 * 后台模块的课程子模块下的团课 Service 接口实现类.
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/2/12 11:30
 */
@Transactional(readOnly = true)
@Service
public class AdminPublicCourseServiceImpl implements AdminPublicCourseService{


  private static Logger logger = LoggerFactory.getLogger(AdminPublicCourseServiceImpl.class);

  @Autowired
  private PublicCourseMapper publicCourseMapper;

  @Autowired
  private CoachMapper coachMapper;

  @Autowired
  private BusinessMapper businessMapper;

  @Autowired
  private RatesMapper ratesMapper;

  @Autowired
  private GymMapper gymMapper;

  @Autowired
  private TakesPublicMapper takesPublicMapper;

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
      final List<PublicCoursePo> publicCoursePoList = publicCourseMapper.selectAll();
      final List<CoachPo> coachPoList = coachMapper.selectAll();
      final List<BusinessPo> businessPoList = businessMapper.selectAll();
      final List<GymPo> gymPoList = gymMapper.getNearGymList();
      final List<CustomerPo> customerPoList = customerMapper.selectAll();


      List<AdminPublicCourseListItemDto> adminPublicCourseListItemDtos = new ArrayList<>();
      for (PublicCoursePo publicCoursePo : publicCoursePoList) {
        AdminPublicCourseListItemDto adminPublicCourseListItemDto = new AdminPublicCourseListItemDto();
        adminPublicCourseListItemDto.setCourseId(publicCoursePo.getId());
        adminPublicCourseListItemDto.setCourseName(publicCoursePo.getName());
        adminPublicCourseListItemDto.setCapacity(publicCoursePo.getCapacity());
        for(CoachPo coachPo:coachPoList){
          if(coachPo.getId() == publicCoursePo.getCoachId()){
            adminPublicCourseListItemDto.setCoachName(coachPo.getName());
            break;
          }
        }
        for (BusinessPo businessPo:businessPoList){
          if (businessPo.getId() == publicCoursePo.getBusinessId()){
            adminPublicCourseListItemDto.setBusinessName(businessPo.getUsername());
            for(GymPo gymPo:gymPoList){
              if(gymPo.getBusinessId() == businessPo.getId()){
                adminPublicCourseListItemDto.setGymName(gymPo.getName());
                break;
              }
            }
          }
          break;
        }


        if (adminPublicCourseListItemDto.getGymName()==null || adminPublicCourseListItemDto.getGymName().equals("")){
          adminPublicCourseListItemDto.setGymName("暂无");
        }

        List<TakesPublicPoKey> takesPublicPoList = takesPublicMapper.selectByCourseId(publicCoursePo.getId());
        for(TakesPublicPoKey takesPublicPo : takesPublicPoList){
          AdminPublicCourseListItemDto temp = new AdminPublicCourseListItemDto();
          BeanUtils.copyProperties(adminPublicCourseListItemDto,temp);
          for(CustomerPo customerPo : customerPoList){
            if (customerPo.getId() == takesPublicPo.getCustomerId()){
              temp.setCustomerName(customerPo.getUsername());
              temp.setCustomerId(customerPo.getId());
              adminPublicCourseListItemDtos.add(temp);
              break;
            }
          }

        }

      }
      res = BaseResult.success("查询团课列表成功");
      res.setData(adminPublicCourseListItemDtos);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询团课列表失败");
    }

    return res;
  }

  @Override
  public BaseResult getDetailByIds(Integer courseId,Integer customerId) {
    BaseResult res = null;

    try {

      PublicCoursePo publicCoursePo = publicCourseMapper.selectByPrimaryKey(courseId);
      CoachPo coachPo = coachMapper.selectByPrimaryKey(publicCoursePo.getCoachId());
      BusinessPo businessPo = businessMapper.selectByPrimaryKey(publicCoursePo.getBusinessId());
      CustomerPo customerPo = customerMapper.selectByPrimaryKey(customerId);
      TakesPublicPoKey takesPublicPoKey = new TakesPublicPoKey();
      takesPublicPoKey.setCustomerId(customerId);
      takesPublicPoKey.setCourseId(courseId);
      PicturePo picturePo = pictureMapper.selectByPrimaryKey(publicCoursePo.getPicId());
      TimeSlotPo timeSlotPo = timeSlotMapper.selectByPrimaryKey(publicCoursePo.getTimeSlotId());

      AdminPublicCourseDetailDto adminPublicCourseDetailDto = new AdminPublicCourseDetailDto();
      adminPublicCourseDetailDto.setBusinessId(businessPo.getId());
      adminPublicCourseDetailDto.setBusinessName(businessPo.getUsername());
      adminPublicCourseDetailDto.setCoachId(coachPo.getId());
      adminPublicCourseDetailDto.setCoachName(coachPo.getName());
      adminPublicCourseDetailDto.setCourseContent(publicCoursePo.getContent());
      adminPublicCourseDetailDto.setCourseId(publicCoursePo.getId());
      adminPublicCourseDetailDto.setCourseName(publicCoursePo.getName());
      adminPublicCourseDetailDto.setCoursePrice(publicCoursePo.getPrice().doubleValue());
      adminPublicCourseDetailDto.setCustomerId(customerId);
      adminPublicCourseDetailDto.setCustomerName(customerPo.getUsername());
      adminPublicCourseDetailDto.setPicUrl(picturePo==null?"":picturePo.getPicLink());
      adminPublicCourseDetailDto.setCapacity(publicCoursePo.getCapacity());
      adminPublicCourseDetailDto.setChosenCount(takesPublicMapper.countByCourseId(courseId));

      Date courseDate = publicCoursePo.getCourseDate();
      String courseTime = new SimpleDateFormat("yyyy/MM/dd").format(courseDate);
      String begin = new SimpleDateFormat("HH:mm").format(timeSlotPo.getBeginTime());
      String end = new SimpleDateFormat("HH:mm").format(timeSlotPo.getEndTime());
      courseTime = courseTime + " " + begin + " - " + end;
      adminPublicCourseDetailDto.setCourseTime(courseTime);


      res = BaseResult.success("查询团课详情成功");
      res.setData(adminPublicCourseDetailDto);
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("查询团课详情失败");
    }

    return res;
  }

  @Override
  public BaseResult getDetailById(Integer id) {
    return null;
  }

  @Override
  public BaseResult deleteItem(Integer id) {
    return null;
  }
}
