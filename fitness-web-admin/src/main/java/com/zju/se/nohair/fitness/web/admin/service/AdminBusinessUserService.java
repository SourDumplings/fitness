package com.zju.se.nohair.fitness.web.admin.service;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.web.admin.abstracts.AbstractService;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCreateBusinessUserDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * 后台模块的用户子模块下的商家用户 Service 接口.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/16 9:34
 */
public interface AdminBusinessUserService extends AbstractService {

  BaseResult saveItem(AdminCreateBusinessUserDto adminCreateBusinessUserDto,
      MultipartFile profilePic, MultipartFile certificationPic);
}
