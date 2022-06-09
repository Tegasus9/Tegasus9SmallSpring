package com.tegasus9.spring.context.event;

import com.tegasus9.spring.context.ApplicationContext;
import com.tegasus9.spring.context.ApplicationEvent;

/**
 * @author XiongYiGe
 * @date 2022/6/9
 * @description
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
