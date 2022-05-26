package com.tegasus9.spring.beans.factory.support;

import com.tegasus9.spring.core.io.Resource;
import com.tegasus9.spring.core.io.ResourceLoader;

/**
 * @author XiongYiGe
 * @date 2022/5/25
 * @description
 */
public interface BeanDefinitionReader {

        BeanDefinitionRegistry getRegistry();

        ResourceLoader getResourceLoader();

        void loadBeanDefinitions(Resource resource);

        void loadBeanDefinitions(String location);
}
