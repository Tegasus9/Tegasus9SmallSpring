package com.tegasus9.spring.beans.factory;

/**
 * @author XiongYiGe
 * @date 2022/6/1
 * @description
 */
public interface InitializingBean {
    /**
     * 在属性填充之后调用
     */
    void afterPropertiesSet();
}
