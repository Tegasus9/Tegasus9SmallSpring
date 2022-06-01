package com.tegasus9.spring.beans.factory.xml;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.XmlUtil;
import com.tegasus9.spring.beans.BeanNotFoundException;
import com.tegasus9.spring.beans.BeanRegisterFailException;
import com.tegasus9.spring.beans.PropertyValue;
import com.tegasus9.spring.beans.factory.config.BeanDefinition;
import com.tegasus9.spring.beans.factory.config.BeanReference;
import com.tegasus9.spring.beans.factory.support.AbstractBeanDefinitionReader;
import com.tegasus9.spring.beans.factory.support.BeanDefinitionRegistry;
import com.tegasus9.spring.core.io.Resource;
import com.tegasus9.spring.core.io.ResourceLoader;
import lombok.extern.log4j.Log4j2;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author XiongYiGe
 * @date 2022/5/25
 * @description
 */
@Log4j2
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {
        try (InputStream inputStream = resource.getInputStream();) {
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | ClassNotFoundException e) {
            throw new BeanRegisterFailException("parseXML fail from:" + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        this.loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations)  {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            // 判断元素
            if (!(childNodes.item(i) instanceof Element)||(!"bean".equals(childNodes.item(i).getNodeName()))) {
                continue;
            }

            // 解析标签
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            String initMethod = bean.getAttribute("init-method");
            String destroyMethodName = bean.getAttribute("destroy-method");

            // 获取 Class，方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            // 优先级 id > name
            String beanName = CharSequenceUtil.isNotEmpty(id) ? id : name;
            if (CharSequenceUtil.isEmpty(beanName)) {
                beanName = CharSequenceUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethodName);
            // 读取属性并填充
            readPropertyAndFillBean(bean, beanDefinition);
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeanNotFoundException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    private void readPropertyAndFillBean(Element bean, BeanDefinition beanDefinition) {
        for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
            if (!(bean.getChildNodes().item(j) instanceof Element) || !"property".equals(bean.getChildNodes().item(j).getNodeName())) {
                continue;
            }
            // 解析标签：property
            Element property = (Element) bean.getChildNodes().item(j);
            String attrName = property.getAttribute("name");
            String attrValue = property.getAttribute("value");
            String attrRef = property.getAttribute("ref");
            // 获取属性值：引入对象、值对象
            Object value = CharSequenceUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
            // 创建属性信息
            PropertyValue propertyValue = new PropertyValue(attrName, value);
            beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
        }
    }
}
