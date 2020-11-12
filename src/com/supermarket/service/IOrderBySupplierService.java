package com.supermarket.service;

import java.sql.SQLException;
@SuppressWarnings("all")
public interface IOrderBySupplierService<T> {
    public T OrderBySupplier() throws SQLException;
    public T OrderBySupplier_getCount() throws SQLException;
    public T listByPage(int currentPage,int pageSize) throws SQLException;
}
