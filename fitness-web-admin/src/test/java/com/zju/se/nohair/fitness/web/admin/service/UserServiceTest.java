package com.zju.se.nohair.fitness.web.admin.service;

import com.zju.se.nohair.fitness.web.admin.TestApplication;
import com.zju.se.nohair.fitness.web.admin.dao.po.User;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/21 15:49
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  public void testListAll() {
    if (userService != null) {
      final List<User> users = userService.listAll();
      System.out.println(users);
    }
  }
}
