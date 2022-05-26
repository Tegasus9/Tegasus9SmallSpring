package com.tegasus9.spring.beans.factory;

import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/5/25
 * @description
 */
public interface ListableBeanFactory extends BeanFactory{


    /**
     * 按照类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type);

    /**
     * Return the names of all beans defined in this registry.
     *
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
