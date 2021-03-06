package com.tegasus9.spring.beans.factory.support;

import com.tegasus9.spring.beans.BeanNotFoundException;
import com.tegasus9.spring.beans.BeanRegisterFailException;
import com.tegasus9.spring.beans.factory.FactoryBean;
import com.tegasus9.spring.beans.factory.config.BeanDefinition;
import com.tegasus9.spring.beans.factory.config.BeanPostProcessor;
import com.tegasus9.spring.beans.factory.config.ConfigurableBeanFactory;
import com.tegasus9.spring.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description 抽象bean工厂。
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private final ClassLoader beanClassLoader = ClassUtils.getClassLoader();

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

    @SuppressWarnings("unchecked")
    private <T> T doGetBean(final String name, final Object[] args){
        Object shareInstance = getSingleton(name);
        if (shareInstance !=null){
            return (T)getObjectForBeanInstance(shareInstance,name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean,name);
    }

    private Object getObjectForBeanInstance(Object beanInstance,String beanName){
        //非代理bean直接返回
        if (!(beanInstance instanceof FactoryBean)){
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);
        if (object==null){
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return object;
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

    public ClassLoader getClassLoader(){
        return this.beanClassLoader;
    }
}
