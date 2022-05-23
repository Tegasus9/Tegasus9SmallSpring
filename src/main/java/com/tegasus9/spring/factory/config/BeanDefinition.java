package com.tegasus9.spring.factory.config;

import com.tegasus9.spring.PropertyValues;
import lombok.Data;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description Bean定义
 */
@Data
public class BeanDefinition {
    private Class<?> beanClass;
    private PropertyValues propertyValues;

    public BeanDefinition(Class<?> clazz){
        this.beanClass = clazz;
        this.propertyValues = new PropertyValues();
    }
    public BeanDefinition(Class<?> clazz,PropertyValues propertyValues){
        this.beanClass = clazz;
        this.propertyValues = propertyValues ==null?new PropertyValues():propertyValues;
    }
}
