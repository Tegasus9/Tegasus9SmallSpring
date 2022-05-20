package com.tegasus9.spring;

import lombok.extern.log4j.Log4j2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description
 */
@Log4j2
public class BeanFactory {
    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public BeanFactory(){
        log.info("初始化BeanFactory成功！");
    }
    public Object getBean(String name){
        log.info("获取Bean：{}",name);
        return beanDefinitionMap.get(name).getBean();
    }
    public void registerBean(String name,BeanDefinition bean){
        beanDefinitionMap.put(name,bean);
        log.info("Bean:{}注册成功！",name);
    }

}
