package com.tegasus9.spring.beans.factory.config;

import com.tegasus9.spring.beans.factory.HierarchicalBeanFactory;

/**
 * @author XiongYiGe
 * @date 2022/5/25
 * @description
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";
}
