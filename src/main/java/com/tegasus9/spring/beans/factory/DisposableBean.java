package com.tegasus9.spring.beans.factory;

/**
 * @author XiongYiGe
 * @date 2022/6/1
 * @description 销毁接口
 *  * Interface to be implemented by beans that want to release resources
 *  * on destruction. A BeanFactory is supposed to invoke the destroy
 *  * method if it disposes a cached singleton. An application context
 *  * is supposed to dispose all of its singletons on close.
 */
public interface DisposableBean {

    void destroy() throws Exception;
}
