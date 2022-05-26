package com.tegasus9.spring.test.common;


import com.tegasus9.spring.beans.PropertyValue;
import com.tegasus9.spring.beans.PropertyValues;
import com.tegasus9.spring.beans.factory.ConfigurableListableBeanFactory;
import com.tegasus9.spring.beans.factory.config.BeanDefinition;
import com.tegasus9.spring.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory){

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：我的修改变量"));
    }

}
