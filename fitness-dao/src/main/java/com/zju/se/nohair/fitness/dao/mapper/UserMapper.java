package com.zju.se.nohair.fitness.dao.mapper;

import com.zju.se.nohair.fitness.commons.persistence.BaseMapper;
import com.zju.se.nohair.fitness.dao.po.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 用户的 Mapper 接口.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/21 15:10
 */
@Mapper
@Component(value = "UserMapper")
public interface UserMapper extends BaseMapper<UserPo> {

  UserPo getUserByPhone(String phone);

}