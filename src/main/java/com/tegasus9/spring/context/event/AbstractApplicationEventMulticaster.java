package com.tegasus9.spring.context.event;

import cn.hutool.core.bean.BeanException;
import com.tegasus9.spring.beans.factory.BeanFactory;
import com.tegasus9.spring.beans.factory.BeanFactoryAware;
import com.tegasus9.spring.context.ApplicationEvent;
import com.tegasus9.spring.context.ApplicationListener;
import com.tegasus9.spring.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author XiongYiGe
 * @date 2022/6/9
 * @description
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
    private BeanFactory beanFactory;

    private final Set<ApplicationListener<? extends ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> applicationListener) {
        applicationListeners.add(applicationListener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> applicationListener) {
        applicationListeners.remove(applicationListener);
    }

    protected Collection<ApplicationListener<? extends ApplicationEvent>> getApplicationEventListeners(ApplicationEvent event){
        LinkedList<ApplicationListener<? extends ApplicationEvent>> listeners = new LinkedList<>();
        for (ApplicationListener<? extends ApplicationEvent> applicationListener : applicationListeners) {
            if (supportEvent(applicationListener,event)){
                listeners.add(applicationListener);
            }
        }
        return listeners;
    }

    private boolean supportEvent(ApplicationListener<? extends ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        //目标监听者class。如是cglib代理类需要获取父类。
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : applicationListener.getClass();

        Type genericInterface = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];

        String typeName = actualTypeArgument.getTypeName();

        Class<?> eventClassName;
        try{
            eventClassName =Class.forName(typeName);
        }catch (ClassNotFoundException e){
            throw new BeanException("wrong event class name: " + typeName);
        }

        //是否可以从指定的类转化而来。
        return eventClassName.isAssignableFrom(event.getClass());


    }

}
