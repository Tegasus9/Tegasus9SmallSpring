package com.tegasus9.spring.factory.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description
 */
@Data
@AllArgsConstructor
public class BeanDefinition {
    private Class<?> bean;
}
