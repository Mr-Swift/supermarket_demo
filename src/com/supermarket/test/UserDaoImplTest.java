package com.supermarket.test;

import com.supermarket.dao.IUserDao;
import com.supermarket.dao.impl.UserDaoImpl;
import com.supermarket.entity.User;
import com.supermarket.factory.ObjectFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }



    @Test
    void listUsers() {
        IUserDao iUserDao = new UserDaoImpl();
        try {
            List list = (List) iUserDao.listUsers();
            for(Object obj:list){
                User user= (User) obj;
                System.out.println(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void listByUserName() {
        IUserDao iUserDao = new UserDaoImpl();
        try {
            List list = (List) iUserDao.listByUserName("jack");
            for(Object obj:list){
                User user= (User) obj;
                System.out.println(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void insertUser() throws SQLException {
        IUserDao iUserDao=new UserDaoImpl();
        iUserDao.insertUser(new User("luc", "123", "男",20,"12345678901","日本","部门经理"));
    }

    @Test
    void modifyById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getBasicQueryStr() {
    }

    @Test
    void testListUsers() {
    }

    @Test
    void testListByUserName() {
    }

    @Test
    void testInsertUser() {
    }

    @Test
    void testModifyById() {
    }

    @Test
    void testDeleteById() {
    }

    @Test
    void getCountsOfId() {
    }

    @Test
    void listByPage() {
    }

    @Test
    void listAuthority() throws SQLException {
        IUserDao iUserDao=new UserDaoImpl();
        List list= (List) iUserDao.listAuthority();
        for (Object obj : list) {
            System.out.println(obj);
        }
    }
}