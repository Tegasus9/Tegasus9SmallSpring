package com.tegasus9.spring.beans;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public class BeanApplyPropertyValueFailException extends RuntimeException{
    public BeanApplyPropertyValueFailException(String msg,Throwable cause){
        super(msg,cause);
    }
}
