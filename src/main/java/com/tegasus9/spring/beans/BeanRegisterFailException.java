package com.tegasus9.spring.beans;


/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description
 */
public class BeanRegisterFailException extends RuntimeException{
    public BeanRegisterFailException(String msg){
        super(msg);
    }
    public BeanRegisterFailException(String msg, Throwable cause){
        super(msg,cause);
    }
}
