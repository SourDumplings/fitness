package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.dao.mapper.UserMapper;
import com.zju.se.nohair.fitness.dao.po.UserPo;
import com.zju.se.nohair.fitness.web.admin.dto.CreateUserDto;
import com.zju.se.nohair.fitness.web.admin.service.UserService;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * 用户 Service 实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/21 15:28
 */
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

  private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  private UserMapper userMapper;

  @Autowired
  public void setUserMapper(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public List<UserPo> listAll() {
    return userMapper.listAll();
  }

  @Override
  public void insert(UserPo entity) {

  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public UserPo getById(Long id) {
    return null;
  }

  @Override
  public void update(UserPo entity) {

  }

  @Override
  public int count() {
    return 0;
  }

  @Override
  public BaseResult getUserByPhone(String phone) {
    UserPo resultUser = userMapper.getUserByPhone(phone);
    BaseResult res = null;

    if (resultUser == null) {
      res = BaseResult.fail("获取用户信息失败");
      return res;
    } else {
      res = BaseResult.success("获取用户信息成功");
      res.setData(resultUser);
    }
    return res;
  }

  @Override
  public BaseResult createUser(CreateUserDto createUserDto) {
    BaseResult res = null;

    try {
      UserPo newUser = new UserPo();
      newUser.setUsername(createUserDto.getUsername());
      newUser.setEmail(createUserDto.getEmail());
      newUser.setPhone(createUserDto.getPhone());

      // 验证通过
      Date date = new Date();
      newUser.setUpdated(date);

      // 密码要加密
      newUser.setPassword(DigestUtils.md5DigestAsHex(createUserDto.getPassword().getBytes()));

      // 新增用户
      newUser.setCreated(date);
      userMapper.insert(newUser);
      res = BaseResult.success("创建用户成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("创建用户失败");
    }

    return res;
  }
}
