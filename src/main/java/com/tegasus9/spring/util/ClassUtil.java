package com.tegasus9.spring.util;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public class ClassUtil {
    public static ClassLoader getClassLoader(){
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            if (cl ==null){
                cl = ClassUtil.class.getClassLoader();
            }
            return  cl;
    }
}
