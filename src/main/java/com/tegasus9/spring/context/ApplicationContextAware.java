package com.tegasus9.spring.context;

import com.tegasus9.spring.beans.factory.Aware;

/**
 * @author XiongYiGe
 * @date 2022/6/6
 * @description
 */
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext);
}
