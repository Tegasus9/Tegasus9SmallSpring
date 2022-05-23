package com.tegasus9.spring.core.io;

import cn.hutool.core.lang.Assert;
import com.tegasus9.spring.util.ClassUtil;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public class ClassPathResource implements Resource{
    private final String path;
    private final ClassLoader classLoader;
    public ClassPathResource(String path){
        this(path, null);
    }
    public ClassPathResource(String path,ClassLoader classLoader){
        Assert.notNull(path,"path not be null.");
        this.path = path;
        this.classLoader = classLoader==null? ClassUtil.getClassLoader():classLoader;
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        InputStream resourceAsStream = classLoader.getResourceAsStream(path);
        if (resourceAsStream==null){
            throw new FileNotFoundException(this.path+" can not open file because file not exist!");
        }
        return resourceAsStream;
    }
}
