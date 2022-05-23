package com.tegasus9.spring.factory;

import com.tegasus9.spring.BeanNotFoundException;
import com.tegasus9.spring.BeanRegisterFailException;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description
 */
public interface BeanFactory {

    Object getBean(String name) throws BeanNotFoundException, BeanRegisterFailException;

    Object getBean(String name,Object... args);
}
