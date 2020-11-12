package com.supermarket.dao;

import com.supermarket.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@SuppressWarnings("all")
public interface IUserDao <T>{
    public T listUsers() throws SQLException;
    public T listByUserName(String userName) throws SQLException;
    public T listById(int id) throws SQLException;
    public T insertUser(User user) throws SQLException;
    public T modifyById(User user) throws SQLException;
    public T deleteById(Set setOfId) throws SQLException;
    public T getCountsOfId() throws SQLException;
    public T listByPage(int currentPage,int pageSize) throws SQLException;
    public T listAuthority() throws SQLException;
}
