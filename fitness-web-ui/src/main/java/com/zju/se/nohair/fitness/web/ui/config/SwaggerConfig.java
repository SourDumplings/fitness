package com.zju.se.nohair.fitness.web.ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger UI 配置类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/25 14:29
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean("后台模块")
  public Docket adminApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("后台模块")
        .apiInfo(apiInfo())
        .pathMapping("/")
        .select()
        //匹配哪些访问的方法
        .paths(PathSelectors.regex("/admin/.*"))
        .build();
  }

  @Bean("商家模块")
  public Docket businessApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("商家模块")
        .apiInfo(apiInfo())
        .pathMapping("/")
        .select()
        //匹配哪些访问的方法
        .paths(PathSelectors.regex("/business/.*"))
        .build();
  }

  @Bean("教练模块")
  public Docket coachApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("教练模块")
        .apiInfo(apiInfo())
        .pathMapping("/")
        .select()
        //匹配哪些访问的方法
        .paths(PathSelectors.regex("/coach/.*"))
        .build();
  }

  @Bean("顾客模块")
  public Docket customerApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("顾客模块")
        .apiInfo(apiInfo())
        .pathMapping("/")
        .select()
        //匹配哪些访问的方法
        .paths(PathSelectors.regex("/customer/.*"))
        .build();
  }

  private ApiInfo apiInfo() {
    //http://localhost:8888/swagger-ui.html
    return new ApiInfoBuilder()
        .title("fitness 接口文档")
        .contact(new Contact("nohair", "no url", "nohair@no_email.com"))
        .description("没有头发也能学 fitness 项目后端接口")
        .version("1.0.0")
        .build();
  }

}