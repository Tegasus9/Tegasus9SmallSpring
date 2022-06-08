package com.tegasus9.spring.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.bean.BeanUtil;
import com.tegasus9.spring.beans.BeanApplyPropertyValueFailException;
import com.tegasus9.spring.beans.BeanRegisterFailException;
import com.tegasus9.spring.beans.PropertyValue;
import com.tegasus9.spring.beans.PropertyValues;
import com.tegasus9.spring.beans.factory.*;
import com.tegasus9.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.tegasus9.spring.beans.factory.config.BeanDefinition;
import com.tegasus9.spring.beans.factory.config.BeanPostProcessor;
import com.tegasus9.spring.beans.factory.config.BeanReference;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeanRegisterFailException {
        Object bean;
        try {
            bean = createBeanInstance(beanDefinition,beanName,args);
            //给bean填充属性值
            applyPropertyValue(beanName,bean,beanDefinition);
            //给bean执行初始化方法和BeanPostProcessor的前置和后置方法
            bean =initialBean(beanName,bean,beanDefinition);

        } catch (Exception e) {
            throw new BeanRegisterFailException(beanName + "bean实例化失败！",e);
        }

        // 注册实现了 DisposableBean 接口的 Bean 对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);


        //只有单例才往单例MAP中取bean。当scope不为单例模式时，不往单例map添加bean。当getBean时。每次都先从单例map拿取，拿不到则新建返回。
        if (beanDefinition.isSingleton()){
            addSingletonToMap(beanName, bean);
        }

        return bean;
    }

    protected void registerDisposableBeanIfNecessary(String beanName,Object bean,BeanDefinition beanDefinition){
        //非singleton不执行销毁方法
        if (!beanDefinition.isSingleton()){
            return;
        }


        if (bean instanceof DisposableBean || StringUtils.isNotBlank(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName,new DisposableBeanAdapter(bean,beanName,beanDefinition));
        }
    }

    private Object initialBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof Aware){
            if (bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getClassLoader());
            }
            if (bean instanceof BeanNameAware){
                ((BeanNameAware) bean).setBeanName(beanName);
            }
            if (bean instanceof BeanFactoryAware){
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
        }

        //处理Bean前置
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        //invokeInitMethods
        invokeInitMethods(beanName,wrappedBean,beanDefinition);
        //处理后置
        return  applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
    }

    public Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName) {
        Object result = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (current==null){
                return result;
            }
            result = current;
        }

        return result;
    }

    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws BeanException {
        if (bean instanceof InitializingBean){
            ((InitializingBean) bean).afterPropertiesSet();
        }

        String initMethodName = beanDefinition.getInitMethodName();
        if (StringUtils.isNotBlank(initMethodName)&&!(bean instanceof InitializingBean)){
            try {
                Method method = beanDefinition.getBeanClass().getMethod(initMethodName);
                method.invoke(bean);
            } catch (Exception e){
                throw new BeanException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'or invoke fail");
            }
        }
    }

    public Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) {
        Object result = bean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
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
