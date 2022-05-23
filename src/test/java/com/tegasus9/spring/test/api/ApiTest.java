package com.tegasus9.spring.test.api;

import com.tegasus9.spring.PropertyValue;
import com.tegasus9.spring.PropertyValues;
import com.tegasus9.spring.beans.factory.config.BeanDefinition;
import com.tegasus9.spring.beans.factory.config.BeanReference;
import com.tegasus9.spring.beans.factory.support.DefaultListableBeanFactory;
import com.tegasus9.spring.test.service.UserDao;
import com.tegasus9.spring.test.service.UserService;
import org.junit.Test;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //注入UserDao
        BeanDefinition userDaoDefinition = new BeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition("userDao",userDaoDefinition);

        //设置userService待注入属性。
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId","10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        // 3. 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 4.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "小傅哥");
        userService.queryUserInfo();
    }



}
