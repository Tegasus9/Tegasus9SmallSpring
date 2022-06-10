package com.tegasus9.spring.aop;

import java.lang.reflect.Method;

/**
 * @author XiongYiGe
 * @date 2022/6/10
 * @description
 */
public interface MethodMatcher {

    boolean matches(Class<?> targetClass, Method method);
}
