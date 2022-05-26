package com.tegasus9.spring.context;

/**
 * @author XiongYiGe
 * @date 2022/5/26
 * @description
 */
public interface ConfigurableApplicationContext extends ApplicationContext{
    /**
     * 刷新容器
     */
    void refresh();
}
