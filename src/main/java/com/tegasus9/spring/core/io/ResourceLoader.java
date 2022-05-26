package com.tegasus9.spring.core.io;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public interface ResourceLoader {
    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";
    Resource getResource(String location);
}
