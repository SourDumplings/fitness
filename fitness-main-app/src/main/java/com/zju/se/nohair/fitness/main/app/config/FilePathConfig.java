package com.zju.se.nohair.fitness.main.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 文件路径与虚拟路径
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/2 21:37
 */
@Configuration
public class FilePathConfig implements WebMvcConfigurer {

  /**
   * 虚拟路径配置
   * @param registry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //linux下的配置
    registry.addResourceHandler("/pic/**").addResourceLocations("file:" + "/root/pic/");
    //windows下的配置
    //registry.addResourceHandler("/pic/**").addResourceLocations("file:" + "D:/data/");
    WebMvcConfigurer.super.addResourceHandlers(registry);
  }
}
