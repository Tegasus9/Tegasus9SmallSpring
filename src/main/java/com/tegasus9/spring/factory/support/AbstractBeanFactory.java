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
        Object singleton = getSingleton(name);
        if (singleton !=null){
            return singleton;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name,beanDefinition);
    }

    protected abstract Object createBean(String name, BeanDefinition beanDefinition) throws BeanRegisterFailException;

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeanNotFoundException;

}
