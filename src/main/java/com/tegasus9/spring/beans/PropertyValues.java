package com.tegasus9.spring.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue){
        propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValueList() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }
    public Object getPropertyValue(String name){
        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyValue.getName().equals(name)){
                return propertyValue.getValue();
            }
        }
        return null;
    }
}
