package com.tegasus9.spring.beans.factory.support;

import com.tegasus9.spring.beans.factory.DisposableBean;
import com.tegasus9.spring.beans.factory.config.BeanDefinition;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

/**
 * @author XiongYiGe
 * @date 2022/6/1
 * @description
 */
@Data
public class DisposableBeanAdapter implements DisposableBean {
    private Object bean;
    private String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition){
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }


    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }

        //防止二次执行
        if (StringUtils.isNotBlank(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))){
            Method method = bean.getClass().getMethod(destroyMethodName);
            method.invoke(bean);
        }
    }
}
