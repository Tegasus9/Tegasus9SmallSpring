package com.tegasus9.spring.aop.framework;

import com.tegasus9.spring.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author XiongYiGe
 * @date 2022/6/10
 * @description
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    private AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }



    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),advisedSupport.getTargetSource().getTargetClass(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (advisedSupport.getMethodMatcher().matches(advisedSupport.getTargetSource().getTarget().getClass(),method)){
            MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,args));
        }
        return method.invoke(advisedSupport.getTargetSource().getTarget(),args);
    }
}
