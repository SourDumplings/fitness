package com.zju.se.nohair.fitness.main.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/21 17:05
 */
@ComponentScan(basePackages = "com.zju.se.nohair.fitness.web.admin.*")
@MapperScan("com.zju.se.nohair.fitness.web.admin.dao.mapper")
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
