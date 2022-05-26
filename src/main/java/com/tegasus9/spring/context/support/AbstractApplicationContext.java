package com.tegasus9.spring.context.support;

import com.tegasus9.spring.beans.factory.ConfigurableListableBeanFactory;
import com.tegasus9.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.tegasus9.spring.beans.factory.config.BeanPostProcessor;
import com.tegasus9.spring.context.ConfigurableApplicationContext;
import com.tegasus9.spring.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/5/26
 * @description
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {


    @Override
    public void refresh() {
        //1.创建beanFactory，加载BeanDefinition
        refreshBeanFactory();

        //2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        //3.在bean实例化之前，执行beanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);
        //4.执行beanPostProcessor
        registerBeanPostProcessor(beanFactory);
        //5.提前实例化单例对象。
        beanFactory.preInstantiateSingletons();
    }

    protected abstract void refreshBeanFactory();

    private void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (Map.Entry<String, BeanPostProcessor> entry : beanPostProcessorMap.entrySet()) {
                beanFactory.addBeanPostProcessor(entry.getValue());
        }
    }

    private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (Map.Entry<String, BeanFactoryPostProcessor> entry : beanFactoryPostProcessorMap.entrySet()) {
            entry.getValue().postProcessBeanFactory(beanFactory);
        }
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name)  {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return getBeanFactory().getBean(name, requiredType);
    }
    
}
