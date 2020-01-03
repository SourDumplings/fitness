package com.zju.se.nohair.fitness.business.service.test;

import com.zju.se.nohair.fitness.business.TestApplication;
import com.zju.se.nohair.fitness.business.service.PublicCourseService;
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
 * @date 2020/1/3 16:52
 */
@Transactional
@Rollback
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class ServiceTest {

  private static Logger logger = LoggerFactory.getLogger(ServiceTest.class);

  @Autowired
  private PublicCourseService publicCourseService;

  /**
   * 测试一下事务方法
   */
  @Test
  public void testTransaction() {
    publicCourseService.acceptResponse(31, 1);
  }
}
