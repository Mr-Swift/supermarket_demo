package com.supermarket.test.servicetest;

import com.supermarket.dao.ISupplierDao;
import com.supermarket.entity.Supplier;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.ISupplierService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierServiceImplTest {

    private ISupplierService iSupplierService = (ISupplierService) ObjectFactory.getObject("ISupplierService");

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listSuppliers() throws SQLException {
        List list= (List) iSupplierService.listSuppliers();
        for(Object obj:list){
            Supplier supplier= (Supplier) obj;
            System.out.println(supplier);
        }
    }

    @Test
    void listById(){
        List list= (List) iSupplierService.listById(8);
        System.out.println(list);

    }

    @Test
    void listSupplierNames() throws SQLException {
        List nameList= (List) iSupplierService.listSupplierNames();
        for(Object obj:nameList){
            String name= (String) obj;
            System.out.println(name);
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