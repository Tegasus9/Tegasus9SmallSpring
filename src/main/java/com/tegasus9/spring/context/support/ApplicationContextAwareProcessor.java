package com.tegasus9.spring.context.support;

import com.tegasus9.spring.beans.factory.config.BeanPostProcessor;
import com.tegasus9.spring.context.ApplicationContext;
import com.tegasus9.spring.context.ApplicationContextAware;

/**
 * @author XiongYiGe
 * @date 2022/6/6
 * @description
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(this.applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
