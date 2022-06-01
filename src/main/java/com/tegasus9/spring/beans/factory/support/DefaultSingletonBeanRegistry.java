package com.tegasus9.spring.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import com.tegasus9.spring.beans.factory.DisposableBean;
import com.tegasus9.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private final Map<String,Object> singletonMap = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    public void registerDisposableBean(String beanName,DisposableBean bean){
        disposableBeans.put(beanName,bean);
    }

    @Override
    public Object getSingleton(String name) {
        return singletonMap.get(name);
    }

    protected void addSingletonToMap(String name,Object object){
            singletonMap.put(name,object);
    }

    public void destroySingletons(){
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (Object disposableBeanName : disposableBeanNames) {
            DisposableBean disposableBean = disposableBeans.remove(disposableBeanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeanException("Destroy method on bean with name '" + disposableBeanName + "' threw an exception", e);
            }
        }


    }
}
