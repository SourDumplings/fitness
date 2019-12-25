package com.zju.se.nohair.fitness.main.app.name;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * 使得 SpringBoot 命名时能够取类全名，避免不同包下类名冲突问题.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/25 18:22
 */

public class UniqueNameGenerator extends AnnotationBeanNameGenerator {

  @Override
  public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
    // 全限定类名
    String beanName = definition.getBeanClassName();
    return beanName;
  }
}
