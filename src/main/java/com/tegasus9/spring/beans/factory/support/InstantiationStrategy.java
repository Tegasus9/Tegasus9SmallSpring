package com.tegasus9.spring.beans.factory.support;

import com.tegasus9.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args);
}
