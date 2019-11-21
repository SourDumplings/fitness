package com.zju.se.nohair.fitness.web.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/21 17:07
 */
@SpringBootApplication
@ComponentScan("com.zju.se.nohair.fitness.web.admin.*")
public class TestApplication {

  public static void main(String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }
}
