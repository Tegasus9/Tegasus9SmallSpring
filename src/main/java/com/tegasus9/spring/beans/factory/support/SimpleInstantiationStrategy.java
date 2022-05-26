package com.tegasus9.spring.beans.factory.support;

import com.tegasus9.spring.beans.BeanRegisterFailException;
import com.tegasus9.spring.beans.factory.config.BeanDefinition;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Constructor;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
@Log4j2
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) {

        Class<?> aClass = beanDefinition.getBeanClass();
        try {
            if (args==null){
                return aClass.getDeclaredConstructor().newInstance();
            }else{
                return aClass.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }
        }catch (Exception e){
            throw new BeanRegisterFailException("instantiate Bean:"+beanName+"fail",e);
        }

    }
}
