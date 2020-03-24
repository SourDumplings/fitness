package com.zju.se.nohair.fitness.coach.service;

import com.zju.se.nohair.fitness.coach.dto.CreateCoachDto;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 教练端-我的 接口
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-17
 */
public interface DetailService {

  BaseResult getCoachDetailByCoachId(Integer coachId);//查看教练的个人信息

  BaseResult createBusinessUser(CreateCoachDto createCoachDto,MultipartFile certificationPic);//注册新教练

  BaseResult createCoachProfilePic(Integer coachId, MultipartFile profilePic);//上传头像

  BaseResult createCoachCertificationPic(Integer coachId, MultipartFile certificationPic);//上传资格证

  BaseResult logInCoach(String username,String password);//教练端登录
}
