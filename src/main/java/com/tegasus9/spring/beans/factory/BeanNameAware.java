package com.tegasus9.spring.beans.factory;

/**
 * @author XiongYiGe
 * @date 2022/6/6
 * @description
 */
public interface BeanNameAware extends Aware{
    void setBeanName(String name);
}
