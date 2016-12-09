package org.zk.jdbc;

import javax.sql.DataSource;

/**
 * Created by Administrator on 12/9/2016.
 */
public class DataSourceFactory {
    private static final DataSource DATA_SOURCE;

    static {
        MyDataSource dataSource = new MyDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:ZK");
        dataSource.setUsername("scott");
        dataSource.setPassword("123456");
        DATA_SOURCE = dataSource;
    }

    public static DataSource getInstance() {
        return DATA_SOURCE;
    }
}
