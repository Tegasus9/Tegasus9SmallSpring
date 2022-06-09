package com.tegasus9.spring.context;

/**
 * @author XiongYiGe
 * @date 2022/6/9
 * @description
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
