package com.tegasus9.spring.factory.support;

import com.tegasus9.spring.BeanRegisterFailException;
import com.tegasus9.spring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition, Object... args) throws BeanRegisterFailException {
        Object instance;
        try {
            instance = createBeanInstance(beanDefinition,name,args);
        } catch (Exception e) {
            throw new BeanRegisterFailException(name + "bean实例化失败！",e);
        }
        addSingletonToMap(name, instance);
        return instance;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object... args) throws NoSuchMethodException {
        Class<?> bean = beanDefinition.getBean();
        Class<?>[] classes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            classes[i] = args[i].getClass();
        }
        Constructor<?> declaredConstructor = bean.getDeclaredConstructor(classes);
        return instantiationStrategy.instantiate(beanDefinition,beanName,declaredConstructor,args);
    }
}
