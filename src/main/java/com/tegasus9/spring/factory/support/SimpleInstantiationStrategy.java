package com.tegasus9.spring.factory.support;

import com.tegasus9.spring.BeanRegisterFailException;
import com.tegasus9.spring.factory.config.BeanDefinition;
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

        Class<?> aClass = beanDefinition.getBean();
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
