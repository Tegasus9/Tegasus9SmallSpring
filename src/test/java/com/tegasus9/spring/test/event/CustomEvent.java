package com.tegasus9.spring.test.event;

import com.tegasus9.spring.context.ApplicationEvent;
import lombok.Getter;
import lombok.Setter;

/**
 * @author XiongYiGe
 * @date 2022/6/9
 * @description
 */
@Getter
@Setter
public class CustomEvent extends ApplicationEvent {
    private String message;
    private Long id;

    public CustomEvent(Object source, String message, Long id) {
        super(source);
        this.message = message;
        this.id = id;
    }


}
