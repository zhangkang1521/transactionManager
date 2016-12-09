package org.zk.dao;

import org.zk.jdbc.DataSourceFactory;
import org.zk.jdbc.DataSourceUtils;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Administrator on 12/9/2016.
 */
public class EmpDao {

    private DataSource dataSource = DataSourceFactory.getInstance();

    public int updateSalary(BigDecimal sal, Integer empno){
        Connection conn = DataSourceUtils.getConnection(dataSource);
        String sql = "update emp set sal=? where empno=?";
        int affectRow = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setBigDecimal(1, sal);
            pstmt.setInt(2, empno);
            affectRow = pstmt.executeUpdate();
        }catch (Exception e) {
            throw new RuntimeException("DataAccessException");
        }
        return affectRow;
    }

    public int updateEname(String ename, Integer empno){
        Connection conn = DataSourceUtils.getConnection(dataSource);
        String sql = "update emp set ename=? where empno=?";
        int affectRow = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ename);
            pstmt.setInt(2, empno);
            affectRow = pstmt.executeUpdate();
            // 模拟出错
            if("error".equals(ename))
                throw new RuntimeException("xx");
        }catch (Exception e) {
            throw new RuntimeException("DataAccessException");
        }
        return affectRow;
    }
}
