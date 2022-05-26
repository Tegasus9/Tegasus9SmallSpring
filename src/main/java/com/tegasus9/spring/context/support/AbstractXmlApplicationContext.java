package com.tegasus9.spring.context.support;

import com.tegasus9.spring.beans.factory.support.DefaultListableBeanFactory;
import com.tegasus9.spring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author XiongYiGe
 * @date 2022/5/26
 * @description
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if(configLocations !=null){
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }
    protected abstract String[] getConfigLocations();
}
