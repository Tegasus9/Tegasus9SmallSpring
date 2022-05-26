package com.tegasus9.spring.beans.factory.support;

import com.tegasus9.spring.BeanNotFoundException;
import com.tegasus9.spring.beans.factory.ConfigurableListableBeanFactory;
import com.tegasus9.spring.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry , ConfigurableListableBeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String name) throws BeanNotFoundException {
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

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        Map<String,T> resultMap = new HashMap<>();
        beanDefinitionMap.forEach((beanName,beanDefinition)->
                {
                    Class<?> beanClass = beanDefinition.getBeanClass();
                    if (type.isAssignableFrom(beanClass)){
                        resultMap.put(beanName, (T) getBean(beanName));
                    }
                }
                );
        return resultMap;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }
}
