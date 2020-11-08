package com.supermarket.test;

import com.supermarket.util.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DbUtilTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getConnection() {
        try {
            Connection connection= DbUtil.getConnection();
            System.out.println(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void closeConnection() {
    }

    @org.junit.jupiter.api.Test
    void closeResult() {
    }
}