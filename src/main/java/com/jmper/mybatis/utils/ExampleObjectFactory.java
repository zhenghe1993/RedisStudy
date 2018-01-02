package com.jmper.mybatis.utils;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2017-12-25 13:57:27)
 */
public class ExampleObjectFactory extends DefaultObjectFactory implements Root {

    @Override
    public <T> T create(Class<T> type) {
        return super.create(type);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    @Override
    public void setProperties(Properties properties) {

        Enumeration<Object> arr = properties.keys();

        while (arr.hasMoreElements()) {
            Object key = arr.nextElement();
            Object value = properties.getProperty((String) key);

            logger.debug("key={},value={}", key, value);
        }

        super.setProperties(properties);
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return super.isCollection(type);
    }
}
