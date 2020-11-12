package com.supermarket.test.servicetest;

import com.supermarket.entity.Account;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IAccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {

    IAccountService iAccountService = (IAccountService) ObjectFactory.getObject("IAccountService");
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listAccounts() {
    }

    @Test
    void listByName() {
    }

    @Test
    void listByPay() {
    }

    @Test
    void insertAccount() {
        Account account=new Account("mac mini 2020",1,6999,"是","苹果中国","性能强劲","2020-11-11");
        try {
            iAccountService.insertAccount(account);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void modifyAccount() {
    }

    @Test
    void deleteByID() {
    }

    @Test
    void listByPage() {
    }

    @Test
    void getCountsOfId() throws SQLException {
        System.out.println(iAccountService.getCountsOfId());
    }
}