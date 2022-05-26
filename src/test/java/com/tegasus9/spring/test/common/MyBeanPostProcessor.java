package com.tegasus9.spring.test.common;


import com.tegasus9.spring.beans.factory.config.BeanPostProcessor;
import com.tegasus9.spring.test.service.UserService;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName){
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：我的位置");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName){
        return bean;
    }

}
