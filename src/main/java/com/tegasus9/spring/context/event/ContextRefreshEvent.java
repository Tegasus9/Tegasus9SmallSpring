package com.tegasus9.spring.context.event;

/**
 * @author XiongYiGe
 * @date 2022/6/9
 * @description
 */
public class ContextRefreshEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshEvent(Object source) {
        super(source);
    }
}
