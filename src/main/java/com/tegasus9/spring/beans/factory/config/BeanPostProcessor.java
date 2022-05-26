package com.tegasus9.spring.beans.factory.config;

/**
 * @author XiongYiGe
 * @date 2022/5/26
 * @description bean后置处理器
 * 用于对新实例化的bean进行扩展
 */
public interface BeanPostProcessor {

    /**
     * 对bean进行初始化之前执行
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessBeforeInitialization(Object bean,String beanName);

    /**
     * 对bean进行初始化之后执行。
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessAfterInitialization(Object bean,String beanName);
}
