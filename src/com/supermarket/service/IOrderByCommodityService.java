package com.supermarket.service;

import java.sql.SQLException;
@SuppressWarnings("all")
public interface IOrderByCommodityService<T> {
    public T groupByCommodity() throws SQLException;
    public T groupByCommodity_getCount() throws SQLException;
    public T listByPage(int currentPage,int pageSize) throws SQLException;
}
