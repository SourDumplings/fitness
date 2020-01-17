package com.zju.se.nohair.fitness.business.service;

import com.zju.se.nohair.fitness.business.dto.CreateBusinessUserDto;
import com.zju.se.nohair.fitness.business.dto.UpdateBusinessUserDetailDto;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商家用户信息相关的服务接口.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/17 17:50
 */
public interface UserInfoService {

  BaseResult createBusinessUser(CreateBusinessUserDto createBusinessUserDto,
      MultipartFile profilePic, MultipartFile certificationPic);

  BaseResult getBusinessUserDetailByBusinessId(Integer businessId);

  BaseResult updateBusinessUserDetail(
      UpdateBusinessUserDetailDto updateBusinessUserDetailDto,
      MultipartFile profilePic, MultipartFile certificationPic);
}
