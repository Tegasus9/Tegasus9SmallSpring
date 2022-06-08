package com.tegasus9.spring.beans.factory;

/**
 * @author XiongYiGe
 * @date 2022/6/8
 * @description 工厂代理bean
 */
public interface FactoryBean<T> {
    T getObject();

    Class<? extends T> getObjectType();

    boolean isSingleton();
}
