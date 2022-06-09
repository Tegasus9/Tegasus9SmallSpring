package com.tegasus9.spring.context.event;

import com.tegasus9.spring.context.ApplicationEvent;
import com.tegasus9.spring.context.ApplicationListener;

/**
 * @author XiongYiGe
 * @date 2022/6/9
 * @description 应用事件广播器
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> applicationListener);

    void removeApplicationListener(ApplicationListener<?> applicationListener);

    void multicastEvent(ApplicationEvent event);
}
