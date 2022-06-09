package com.tegasus9.spring.context;

import com.tegasus9.spring.beans.factory.HierarchicalBeanFactory;
import com.tegasus9.spring.beans.factory.ListableBeanFactory;
import com.tegasus9.spring.core.io.ResourceLoader;

/**
 * @author XiongYiGe
 * @date 2022/5/26
 * @description 应用上下文
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
