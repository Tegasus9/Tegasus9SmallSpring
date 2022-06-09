package com.tegasus9.spring.test.event;

import com.tegasus9.spring.context.ApplicationListener;
import com.tegasus9.spring.context.event.ContextCloseEvent;

/**
 * @author XiongYiGe
 * @date 2022/6/9
 * @description
 */
public class ContextCloseEventListener  implements ApplicationListener<ContextCloseEvent> {
    @Override
    public void onApplicationEvent(ContextCloseEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }
}
