package com.tegasus9.spring.factory.support;

import com.tegasus9.spring.BeanNotFoundException;
import com.tegasus9.spring.BeanRegisterFailException;
import com.tegasus9.spring.factory.BeanFactory;
import com.tegasus9.spring.factory.config.BeanDefinition;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeanNotFoundException, BeanRegisterFailException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name,args);
    }

    private Object doGetBean(final String name,final Object[] args){
        Object singleton = getSingleton(name);
        if (singleton !=null){
            return singleton;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name,beanDefinition,args);
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeanNotFoundException;

    protected abstract Object createBean(String name, BeanDefinition beanDefinition,Object... args) throws BeanRegisterFailException;
}
