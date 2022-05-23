package com.tegasus9.spring.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.tegasus9.spring.BeanApplyPropertyValueFailException;
import com.tegasus9.spring.BeanRegisterFailException;
import com.tegasus9.spring.PropertyValue;
import com.tegasus9.spring.PropertyValues;
import com.tegasus9.spring.factory.config.BeanDefinition;
import com.tegasus9.spring.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition, Object... args) throws BeanRegisterFailException {
        Object instance;
        try {
            instance = createBeanInstance(beanDefinition,name,args);

            //给bean填充属性值
            applyPropertyValue(name,instance,beanDefinition);
        } catch (Exception e) {
            throw new BeanRegisterFailException(name + "bean实例化失败！",e);
        }
        addSingletonToMap(name, instance);
        return instance;
    }

    private void applyPropertyValue(String beanName, Object instance, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValueList()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                //当A依赖B时。value值获取B的Bean实例
                if (value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(instance,name,value);
            }
        } catch (Exception e){
            throw new BeanApplyPropertyValueFailException("Bean:{"+beanName+"}注入属性失败！",e);
        }


    }

    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object... args) throws NoSuchMethodException {
        Class<?> bean = beanDefinition.getBeanClass();
        Constructor<?> declaredConstructor;
        //参数为空时不需要传入默认构造器以适配cglib实现方法。
        if (args==null){
            declaredConstructor = null;
        }
        else {
            Class<?>[] classes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classes[i] = args[i].getClass();
            }
            declaredConstructor = bean.getDeclaredConstructor(classes);
        }

        return instantiationStrategy.instantiate(beanDefinition,beanName,declaredConstructor,args);
    }
}
