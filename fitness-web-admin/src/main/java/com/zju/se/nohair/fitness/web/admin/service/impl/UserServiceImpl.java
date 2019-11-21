package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.web.admin.dao.mapper.UserMapper;
import com.zju.se.nohair.fitness.web.admin.dao.po.User;
import com.zju.se.nohair.fitness.web.admin.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户 Service 实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/21 15:28
 */
@Service
public class UserServiceImpl implements UserService {

  private UserMapper userMapper;

  @Autowired
  public void setUserMapper(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public List<User> listAll() {
    return userMapper.listAll();
  }

  @Override
  public void insert(User entity) {

  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public User getById(Long id) {
    return null;
  }

  @Override
  public void update(User entity) {

  }

  @Override
  public int count() {
    return 0;
  }
}
