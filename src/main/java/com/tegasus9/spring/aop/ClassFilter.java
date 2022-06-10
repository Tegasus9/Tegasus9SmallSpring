package com.tegasus9.spring.aop;

/**
 * @author XiongYiGe
 * @date 2022/6/10
 * @description 类过滤器
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
