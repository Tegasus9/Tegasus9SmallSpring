package com.tegasus9.spring.factory.support;

import com.tegasus9.spring.factory.config.BeanDefinition;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description
 */
public interface BeanDefinitionRegistry {

    void registryBeanDefinition(String name, BeanDefinition beanDefinition);
}
