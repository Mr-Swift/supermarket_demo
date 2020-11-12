package com.supermarket.test.daotest;

import com.supermarket.dao.IOrderByCommodityDao;
import com.supermarket.dao.impl.OrderByCommodityDaoImpl;
import com.supermarket.entity.GroupByCommodity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderByCommodityDaoImplTest {
    IOrderByCommodityDao iOrderByCommodityDao=new OrderByCommodityDaoImpl();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void groupByCommodity_getCount() throws SQLException {
        List list= (List) iOrderByCommodityDao.listByPage(1,5);
        for(Object obj:list){
            GroupByCommodity groupByCommodity= (GroupByCommodity) obj;
            System.out.println(groupByCommodity);
        }
    }

    @Test
    void groupByCommodity() throws SQLException {
        List list= (List) iOrderByCommodityDao.groupByCommodity();
        for(Object obj:list){
            GroupByCommodity groupByCommodity= (GroupByCommodity) obj;
            System.out.println(groupByCommodity);
        }
    }

    @Test
    void listByPage() {
    }
}