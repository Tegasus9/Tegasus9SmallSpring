package com.tegasus9.spring.beans.factory.support;

import com.tegasus9.spring.beans.factory.config.BeanDefinition;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String name, BeanDefinition beanDefinition);

    /**
     * 判断是否包含指定名称的BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);
}
