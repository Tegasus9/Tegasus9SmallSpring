package com.tegasus9.spring.test.event;

import com.tegasus9.spring.context.ApplicationListener;
import com.tegasus9.spring.context.event.ContextRefreshEvent;

/**
 * @author XiongYiGe
 * @date 2022/6/9
 * @description
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
