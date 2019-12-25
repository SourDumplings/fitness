package com.zju.se.nohair.fitness.main.app;

import com.zju.se.nohair.fitness.main.app.name.UniqueNameGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 主启动类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/21 17:05
 */
@ComponentScan(basePackages = "com.zju.se.nohair.fitness.*", nameGenerator = UniqueNameGenerator.class)
@MapperScan("com.zju.se.nohair.fitness.dao.mapper")
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
