package com.supermarket.test.servicetest;

import com.supermarket.dao.IUserDao;
import com.supermarket.entity.User;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    IUserService iUserService= (IUserService) ObjectFactory.getObject("IUserService");



    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listUsers() throws SQLException {
        List<User> list = (List<User>) iUserService.listUsers();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    void listByUserName() {
    }

    @Test
    void insertUser() {
    }

    @Test
    void modifyById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getCountsOfId() {
    }

    @Test
    void listByPage() {
    }

    @Test
    void listAuthority() throws SQLException {

        List list= (List) iUserService.listAuthority();
        for (Object obj : list) {
            System.out.println(obj);
        }
    }
}