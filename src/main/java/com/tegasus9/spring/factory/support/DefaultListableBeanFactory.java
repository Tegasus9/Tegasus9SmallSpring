package com.tegasus9.spring.factory.support;

import com.tegasus9.spring.BeanNotFoundException;
import com.tegasus9.spring.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{
    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String name) throws BeanNotFoundException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition==null){
            throw new BeanNotFoundException("bean:"+name+"not found!");
        }
        return beanDefinition;
    }



    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name,beanDefinition);
    }
}
