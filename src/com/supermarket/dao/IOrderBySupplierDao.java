package com.supermarket.dao;

import java.sql.SQLException;
@SuppressWarnings("all")
public interface IOrderBySupplierDao <T>{
    public T OrderBySupplier() throws SQLException;
    public T OrderBySupplier_getCount() throws SQLException;
    public T listByPage(int currentPage,int pageSize) throws SQLException;
}
