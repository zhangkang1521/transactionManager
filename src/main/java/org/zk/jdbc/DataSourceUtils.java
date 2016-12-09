package org.zk.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 获取与关闭数据库连接，数据库连接与当前线程绑定
 * <p>如果是要实现事务管理，必须是同一个连接，不能再打开一个连接
 * <p>Created by Administrator on 11/19/2016.
 */
public class DataSourceUtils {

    private static ThreadLocal threadConnection = new ThreadLocal();

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceUtils.class);

    /**
     * 获取数据库连接，如果当前线程已绑定，则返回存在的连接，否则获取一个新的连接
     * @param dataSource
     * @return
     */
    public static Connection getConnection(DataSource dataSource){
        Connection conn = (Connection) threadConnection.get();
        if(conn != null) {
            LOGGER.info("获取数据库连接，使用当前线程中已绑定的连接，{}", conn);
            return conn;
        } else {
            try {
                conn = dataSource.getConnection();
                threadConnection.set(conn);
            } catch (SQLException e) {
                throw new RuntimeException("获取数据库连接失败");
            }
            LOGGER.info("打开新的数据库连接，{}", conn);
            return conn;
        }
    }

    /**
     * 关闭当前线程中绑定的数据库连接
     */
    public static void closeConnection() {
        Connection conn = (Connection) threadConnection.get();
        if(conn != null) {
            try {
                conn.close();
                LOGGER.info("关闭数据库连接，{}", conn);
            } catch (SQLException e) {
                LOGGER.error("关闭数据库异常");
            }
        }
    }
}
