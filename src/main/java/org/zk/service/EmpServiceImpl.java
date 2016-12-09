package org.zk.service;

import org.zk.dao.EmpDao;

import java.math.BigDecimal;

/**
 * Created by Administrator on 12/9/2016.
 */
public class EmpServiceImpl implements EmpService{

    EmpDao empDao = new EmpDao();

    public void save(BigDecimal sal, String ename, Integer empNo) {
        empDao.updateSalary(sal, empNo);
        empDao.updateEname(ename, empNo);
    }
}
