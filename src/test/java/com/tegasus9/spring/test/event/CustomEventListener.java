package com.tegasus9.spring.test.event;

import com.tegasus9.spring.context.ApplicationListener;

/**
 * @author XiongYiGe
 * @date 2022/6/9
 * @description
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到一条消息！来源："+event.getSource());
        System.out.println("event = " + event);
    }
}
