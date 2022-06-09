package com.tegasus9.spring.context.support;

import com.tegasus9.spring.beans.factory.ConfigurableListableBeanFactory;
import com.tegasus9.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.tegasus9.spring.beans.factory.config.BeanPostProcessor;
import com.tegasus9.spring.context.ApplicationEvent;
import com.tegasus9.spring.context.ApplicationListener;
import com.tegasus9.spring.context.ConfigurableApplicationContext;
import com.tegasus9.spring.context.event.ApplicationEventMulticaster;
import com.tegasus9.spring.context.event.ContextCloseEvent;
import com.tegasus9.spring.context.event.ContextRefreshEvent;
import com.tegasus9.spring.context.event.SimpleApplicationEventMulticaster;
import com.tegasus9.spring.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/5/26
 * @description
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() {
        //1.创建beanFactory，加载BeanDefinition
        refreshBeanFactory();

        //2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 新增感知aware
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //4.在bean实例化之前，执行beanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);
        //5. 执行beanPostProcessor
        registerBeanPostProcessor(beanFactory);

        // 6.初始化事件发布者
        initApplicationEventMulticaster();

        // 7. 注册监听器
        registerListeners();


        //8..提前实例化单例对象。
        beanFactory.preInstantiateSingletons();

        // 9.发布容器刷新完成事件
        finishRefresh();
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    private void registerListeners() {
        Map<String, ApplicationListener> beans = getBeansOfType(ApplicationListener.class);
        for (Map.Entry<String, ApplicationListener> listenerEntry : beans.entrySet()) {
            applicationEventMulticaster.addApplicationListener(listenerEntry.getValue());
        }
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,applicationEventMulticaster);
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

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        publishEvent(new ContextCloseEvent(this));

        getBeanFactory().destroySingletons();
    }
}
