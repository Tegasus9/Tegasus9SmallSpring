package com.tegasus9.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description
 */
@AllArgsConstructor
@Getter
public class BeanRegisterFailException extends Exception{
    private final String message;
}
