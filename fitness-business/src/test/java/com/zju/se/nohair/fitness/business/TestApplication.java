package com.zju.se.nohair.fitness.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/3 16:53
 */
@SpringBootApplication
@ComponentScan("com.zju.se.nohair.fitness.business.*")
@MapperScan({"com.zju.se.nohair.fitness.dao.mapper"})
public class TestApplication {

  public static void main(String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }
}
