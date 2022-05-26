package com.tegasus9.spring.beans.factory.config;

import com.tegasus9.spring.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author XiongYiGe
 * @date 2022/5/26
 * @description bean工厂后置处理器
 * 允许对beanDefinition进行自定义修改
 */
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory);
}
