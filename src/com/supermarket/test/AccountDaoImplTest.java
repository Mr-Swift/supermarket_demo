package com.supermarket.test;

import com.supermarket.dao.IAccountDao;
import com.supermarket.dao.impl.AccountDaoImpl;
import com.supermarket.entity.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SuppressWarnings("all")

class AccountDaoImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listAccounts() {
        IAccountDao iAccountDao=new AccountDaoImpl();
        try {
            List list= (List) iAccountDao.listAccounts();
            for(Object obj:list){
                Account account= (Account) obj;
                System.out.println(account);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void listByName() {
    }

    @Test
    void listByPay() {
    }

    @Test
    void insertAccount() {
    }

    @Test
    void modifyAccount() {
    }

    @Test
    void deleteByID() {
    }
}