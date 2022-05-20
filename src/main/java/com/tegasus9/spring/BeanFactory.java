package com.tegasus9.spring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description
 */
public class BeanFactory {
    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public BeanFactory(){
        System.out.println("初始化BeanFactory成功！");
    }
    public Object getBean(String name){
        System.out.println("获取Bean："+name);
        return beanDefinitionMap.get(name).getBean();
    }
    public void registerBean(String name,BeanDefinition bean){
        beanDefinitionMap.put(name,bean);
        System.out.println(name+"Bean注册成功！");
    }

}
