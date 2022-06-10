package com.tegasus9.spring.aop;

/**
 * @author XiongYiGe
 * @date 2022/6/10
 * @description
 */
public class TargetSource {
    private final Object target;


    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass(){
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget(){
        return this.target;
    }
}
