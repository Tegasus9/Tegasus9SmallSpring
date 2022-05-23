package com.tegasus9.spring.beans.factory.config;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description 单例注册表
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String name);
}
