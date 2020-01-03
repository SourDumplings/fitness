package com.zju.se.nohair.fitness.web.admin.service.test;

import com.zju.se.nohair.fitness.dao.po.UserPo;
import com.zju.se.nohair.fitness.web.admin.TestApplication;
import com.zju.se.nohair.fitness.web.admin.service.UserService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/21 15:49
 */
@Transactional
@Rollback
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {

  private static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

  @Autowired
  private UserService userService;

  @Test
  public void testListAll() {
    if (userService != null) {
      final List<UserPo> users = userService.listAll();
      logger.info(users.toString());
    }
  }
}
