package com.tegasus9.spring.factory.support;

import com.tegasus9.spring.BeanRegisterFailException;
import com.tegasus9.spring.factory.config.BeanDefinition;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) throws BeanRegisterFailException {
        Object instance;
        try {
            instance = beanDefinition.getBean().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeanRegisterFailException(name + "bean实例化失败！",e);
        }
        addSingletonToMap(name, instance);
        return instance;
    }
}
