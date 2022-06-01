package com.tegasus9.spring.test.api;


import com.tegasus9.spring.beans.factory.support.DefaultListableBeanFactory;
import com.tegasus9.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.tegasus9.spring.context.support.ClassPathXmlApplicationContext;
import com.tegasus9.spring.test.common.MyBeanFactoryPostProcessor;
import com.tegasus9.spring.test.common.MyBeanPostProcessor;
import com.tegasus9.spring.test.service.UserService;
import org.junit.Test;

import java.util.LinkedList;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
public class ApiTest {

    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化之后，修改 Bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void testThreadLocal() throws InterruptedException {
        ThreadLocal<Integer> integerThreadLocal = ThreadLocal.withInitial(()->100);
        int a = 100;
        int i =0;
        LinkedList<byte[]> objects = new LinkedList<>();
        while (true){
            System.out.println("integerThreadLocal.get() = " + integerThreadLocal.get());
            System.out.println("i = " + i);
            objects.add(new byte[5 * 1024 * 1024]);
            Thread.sleep(1);
            if (i++>100){
                System.gc();
            }
        }

    }

}
