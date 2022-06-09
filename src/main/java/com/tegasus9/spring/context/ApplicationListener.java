package com.tegasus9.spring.context;

import java.util.EventListener;

/**
 * @author XiongYiGe
 * @date 2022/6/9
 * @description
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
