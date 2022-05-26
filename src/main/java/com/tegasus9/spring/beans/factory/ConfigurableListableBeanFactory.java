package com.tegasus9.spring.beans.factory;

import com.tegasus9.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.tegasus9.spring.beans.factory.config.BeanDefinition;
import com.tegasus9.spring.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author XiongYiGe
 * @date 2022/5/25
 * @description
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName);


}
