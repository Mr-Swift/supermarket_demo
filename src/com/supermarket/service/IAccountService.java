package com.supermarket.service;

import com.supermarket.entity.Account;

import java.sql.SQLException;
import java.util.Set;
@SuppressWarnings("all")
public interface IAccountService<T> {
    public T listAccounts() throws SQLException;
    public T listByName(String accountName) throws SQLException;
    public T listByPay(String pay) throws SQLException;
    public T insertAccount(Account account) throws SQLException;
    public T modifyAccount(Account account) throws SQLException;
    public T deleteByID(Set setOfId) throws SQLException;
    public T listByPage(int currentPage,int pageSize) throws SQLException;
    public T getCountsOfId() throws SQLException;
}
