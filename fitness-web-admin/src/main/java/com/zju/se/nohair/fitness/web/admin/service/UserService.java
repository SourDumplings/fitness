package com.zju.se.nohair.fitness.web.admin.service;

import com.zju.se.nohair.fitness.dao.po.UserPo;
import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.commons.persistence.BaseService;
import com.zju.se.nohair.fitness.web.admin.dto.CreateUserDto;

/**
 * 用户 Service 接口.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/21 15:27
 */
public interface UserService extends BaseService<UserPo> {

  BaseResult getUserByPhone(String phone);

  BaseResult createUser(CreateUserDto createUserDto);

}
