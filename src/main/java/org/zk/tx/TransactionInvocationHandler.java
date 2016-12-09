package org.zk.tx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zk.jdbc.DataSourceFactory;
import org.zk.jdbc.DataSourceUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

/**
 * 使用jdk的动态代理实现事务切面
 * Created by Administrator on 12/9/2016.
 */
public class TransactionInvocationHandler implements InvocationHandler {

    private Object target; // 要被代理的对象
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionInvocationHandler.class);

    public TransactionInvocationHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Connection conn = null;
        Object result = null;
        try {
            conn = DataSourceUtils.getConnection(DataSourceFactory.getInstance());
            conn.setAutoCommit(false);
            LOGGER.info("开始事务");
            result = method.invoke(target, args);
            conn.commit();
            conn.setAutoCommit(true);
            LOGGER.info("提交事务");
        } catch (Exception e) {
            conn.rollback();
            LOGGER.error("回滚事务", e);
        } finally {
            DataSourceUtils.closeConnection();
        }
        return result;
    }
}
