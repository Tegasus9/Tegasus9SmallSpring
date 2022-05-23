package com.tegasus9.spring.beans.factory.config;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description beanReference
 */
public class BeanReference {
    private final String beanName;
    public BeanReference(String beanName){
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
