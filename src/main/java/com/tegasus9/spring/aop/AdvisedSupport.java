package com.tegasus9.spring.aop;

import lombok.Data;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author XiongYiGe
 * @date 2022/6/10
 * @description
 */
@Data
public class AdvisedSupport {
    private MethodMatcher methodMatcher;
    private TargetSource targetSource;
    private MethodInterceptor methodInterceptor;
}
