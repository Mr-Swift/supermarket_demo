package com.supermarket.test.servicetest;

import com.supermarket.entity.GroupByCommodity;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IOrderByCommodityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderByCommodityServiceImplTest {

    IOrderByCommodityService iOrderByCommodityService = (IOrderByCommodityService) ObjectFactory.getObject("IOrderByCommodityService");

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void groupByCommodity_getCount() {
    }

    @Test
    void groupByCommodity() throws SQLException {
        List list= (List) iOrderByCommodityService.groupByCommodity();
        for(Object obj:list){
            GroupByCommodity groupByCommodity= (GroupByCommodity) obj;
            System.out.println(groupByCommodity);
        }

    }

    @Test
    void listByPage() {
    }
}