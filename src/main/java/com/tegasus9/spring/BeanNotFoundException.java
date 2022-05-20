package com.tegasus9.spring;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description
 */
@Getter
@AllArgsConstructor
public class BeanNotFoundException extends Exception{
    private String message;
}
