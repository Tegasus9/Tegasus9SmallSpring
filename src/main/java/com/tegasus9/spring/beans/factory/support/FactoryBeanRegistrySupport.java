package com.tegasus9.spring.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import com.tegasus9.spring.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author XiongYiGe
 * @date 2022/6/8
 * @description
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{

    private final Map<String,Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getObjectFromFactoryBean(FactoryBean<?> factoryBean ,String name){
        if (factoryBean.isSingleton()){
            Object beanObject = this.factoryBeanObjectCache.get(name);
            if (beanObject==null){
                beanObject = doGetObjectFromFactoryBean(factoryBean,name);
                this.factoryBeanObjectCache.put(name,((beanObject!=null)?beanObject:NULL_OBJECT));
            }
            return ((beanObject!=NULL_OBJECT)?beanObject:null);
        }
        else{
            return doGetObjectFromFactoryBean(factoryBean,name);
        }
    }

    protected Object getCachedObjectForFactoryBean(String name){
        Object object = factoryBeanObjectCache.get(name);
        return ((object!=NULL_OBJECT)?object:null);
    }


    private Object doGetObjectFromFactoryBean(final FactoryBean<?> factoryBean,final String beanName){
        try {
            return factoryBean.getObject();
        }catch (Exception e){
            throw new BeanException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }




}
