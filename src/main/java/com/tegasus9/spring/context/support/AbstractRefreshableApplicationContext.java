package com.tegasus9.spring.context.support;

import com.tegasus9.spring.beans.factory.ConfigurableListableBeanFactory;
import com.tegasus9.spring.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author XiongYiGe
 * @date 2022/5/26
 * @description
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() {
        DefaultListableBeanFactory newBeanFactory = createBeanFactory();
        loadBeanDefinitions(newBeanFactory);
        this.beanFactory = newBeanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
