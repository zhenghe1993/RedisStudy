package com.jmper.mybatis.utils;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2017-12-25 22:32:54)
 */
// ExamplePlugin.java
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class})})
public class ExamplePlugin implements Interceptor, Root {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        logger.info("mybatis intercept sql:{}", sql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        logger.debug("AAAAAAAAAAAAAAA  -=-- {}",o);
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        Enumeration<Object> arr = properties.keys();

        while (arr.hasMoreElements()) {
            Object key = arr.nextElement();
            Object value = properties.getProperty((String) key);

            logger.debug("key={},value={}", key, value);
        }
    }
}