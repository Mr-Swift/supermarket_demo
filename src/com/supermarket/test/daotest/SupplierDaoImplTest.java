package com.supermarket.test.daotest;

import com.supermarket.dao.ISupplierDao;
import com.supermarket.dao.impl.SupplierDaoImpl;
import com.supermarket.entity.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.function.IntSupplier;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoImplTest {
    ISupplierDao iSupplierDao=new SupplierDaoImpl();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getBasicQueryStr() {
    }

    @Test
    void listSuppliers() throws SQLException {
        List list= (List) iSupplierDao.listSuppliers();
        for(Object obj:list){
            Supplier supplier= (Supplier) obj;
            System.out.println(supplier);
        }
    }

    @Test
    void listSupplierNames() throws SQLException {
        List list= (List) iSupplierDao.listSupplierNames();
        for(Object obj:list){
            String  str= (String) obj;
            System.out.println(str);
        }
    }

    @Test
    void listBySupplierName() {
    }

    @Test
    void insertSupplier() {
    }

    @Test
    void modifySupplier() {
    }

    @Test
    void deleteSupplier() {
    }

    @Test
    void getCountsOfId() {
    }

    @Test
    void listByPage() {
    }
}