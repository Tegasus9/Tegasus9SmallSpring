package com.tegasus9.spring.beans.factory.support;

import com.tegasus9.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private final Map<String,Object> singletonMap = new HashMap<>();

    @Override
    public Object getSingleton(String name) {
        return singletonMap.get(name);
    }

    protected void addSingletonToMap(String name,Object object){
            singletonMap.put(name,object);
    }
}
