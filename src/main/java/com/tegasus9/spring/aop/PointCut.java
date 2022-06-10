package com.tegasus9.spring.aop;

/**
 * @author XiongYiGe
 * @date 2022/6/10
 * @description
 */
public interface PointCut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
