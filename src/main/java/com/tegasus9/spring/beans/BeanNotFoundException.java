package com.tegasus9.spring.beans;


/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description
 */

public class BeanNotFoundException extends RuntimeException{
    public BeanNotFoundException(String msg){
        super(msg);
    }
}
