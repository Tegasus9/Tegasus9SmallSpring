package com.tegasus9.spring.context.event;

import com.tegasus9.spring.beans.factory.BeanFactory;
import com.tegasus9.spring.context.ApplicationEvent;
import com.tegasus9.spring.context.ApplicationListener;

/**
 * @author XiongYiGe
 * @date 2022/6/9
 * @description
 */

public class SimpleApplicationEventMulticaster extends  AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory){
        setBeanFactory(beanFactory);
    }
    @Override
    @SuppressWarnings({"unchecked"})
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationEventListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
