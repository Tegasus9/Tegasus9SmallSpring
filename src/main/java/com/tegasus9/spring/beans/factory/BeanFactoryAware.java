package com.tegasus9.spring.beans.factory;

/**
 * @author XiongYiGe
 * @date 2022/6/6
 * @description
 */
public interface BeanFactoryAware extends Aware{
    void setBeanFactory(BeanFactory beanFactory);
}
