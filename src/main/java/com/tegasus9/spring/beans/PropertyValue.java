package com.tegasus9.spring.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
@Data
@AllArgsConstructor
public class PropertyValue {
    private final String name;
    private final Object value;
}
