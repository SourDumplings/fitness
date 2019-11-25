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

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .pathMapping("/")
        .select()
        //匹配那些访问的方法
        .paths(PathSelectors.regex("/.*"))
        .build();
  }

  private ApiInfo apiInfo() {
    //http://localhost:8888/swagger-ui.html
    return new ApiInfoBuilder().title("接口文档")
        .contact(new Contact("nohair", "no url", "nohair@no_email.com"))
        .description("没有头发也能学 fitness 项目后端接口")
        .version("1.0.0")
        .build();
  }

}