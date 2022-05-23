package com.tegasus9.spring.test.api;

import com.tegasus9.spring.factory.BeanFactory;
import com.tegasus9.spring.factory.config.BeanDefinition;
import com.tegasus9.spring.factory.support.DefaultListableBeanFactory;
import com.tegasus9.spring.test.service.UserService;
import org.junit.Test;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public class ApiTest {

    @Test
    public void test_factory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService",beanDefinition);

        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.systemOut();

        UserService userService1 = (UserService) beanFactory.getSingleton("userService");
        userService1.systemOut();
    }
}
