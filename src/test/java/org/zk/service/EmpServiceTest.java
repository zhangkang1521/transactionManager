package org.zk.service;

import org.junit.Test;
import org.zk.tx.TransactionInvocationHandler;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 12/9/2016.
 */
public class EmpServiceTest {

    @Test
    public void testSave() {
        EmpService empService = (EmpService) Proxy.newProxyInstance(EmpService.class.getClassLoader(), new Class[]{EmpService.class},
                new TransactionInvocationHandler(new EmpServiceImpl()));
        empService.save(new BigDecimal(301), "error", 7369);
    }
}