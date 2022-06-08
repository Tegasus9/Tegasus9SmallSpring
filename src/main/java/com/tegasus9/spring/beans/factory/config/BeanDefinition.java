package com.tegasus9.spring.beans.factory.config;

import com.tegasus9.spring.beans.PropertyValues;
import lombok.Data;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description Bean定义
 */
@Data
public class BeanDefinition {

    private static final String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    private static final String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;
    private Class<?> beanClass;
    private PropertyValues propertyValues;

    private String initMethodName;

    private String destroyMethodName;

    private String scope =SCOPE_SINGLETON;

    private boolean singleton = true;

    private boolean prototype = false;

    public void setScope(String scope) {
        this.scope = scope;
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
        this.singleton = SCOPE_SINGLETON.equals(scope);
    }

    public boolean isSingleton() {
        return singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }
    public BeanDefinition(Class<?> clazz){
        this.beanClass = clazz;
        this.propertyValues = new PropertyValues();
    }
    public BeanDefinition(Class<?> clazz,PropertyValues propertyValues){
        this.beanClass = clazz;
        this.propertyValues = propertyValues ==null?new PropertyValues():propertyValues;
    }
}
