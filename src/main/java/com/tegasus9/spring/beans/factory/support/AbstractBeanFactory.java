package com.tegasus9.spring.beans.factory.support;

import com.tegasus9.spring.beans.BeanNotFoundException;
import com.tegasus9.spring.beans.BeanRegisterFailException;
import com.tegasus9.spring.beans.factory.config.BeanDefinition;
import com.tegasus9.spring.beans.factory.config.BeanPostProcessor;
import com.tegasus9.spring.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeanNotFoundException, BeanRegisterFailException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name,args);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(String name, Class<T> requiredType) {
        return (T) getBean(name);
    }

    private Object doGetBean(final String name, final Object[] args){
        Object singleton = getSingleton(name);
        if (singleton !=null){
            return singleton;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name,beanDefinition,args);
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeanNotFoundException;

    protected abstract Object createBean(String name, BeanDefinition beanDefinition,Object... args) throws BeanRegisterFailException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        //beanPostProcessor若有则先移除再放置队尾。
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}
