package com.zju.se.nohair.fitness.business.service;

import com.zju.se.nohair.fitness.business.dto.ChangePasswordDto;
import com.zju.se.nohair.fitness.business.dto.CreateBusinessUserDto;
import com.zju.se.nohair.fitness.business.dto.LoginDto;
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

  BaseResult login(LoginDto loginDto);

  BaseResult changePasssword(ChangePasswordDto changePasswordDto);

  BaseResult createBusinessUser(CreateBusinessUserDto createBusinessUserDto,
      MultipartFile certificationPic);

  BaseResult uploadProfilePic(Integer businessId, MultipartFile profilePic);

  BaseResult uploadCertificationPic(Integer businessId, MultipartFile certificationPic);

  BaseResult getBusinessUserDetailByBusinessId(Integer businessId);
}
