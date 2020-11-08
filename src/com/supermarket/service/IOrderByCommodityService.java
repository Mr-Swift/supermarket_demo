package com.supermarket.service;

import java.sql.SQLException;

public interface IOrderByCommodityService<T> {
    public T groupByCommodity() throws SQLException;
}
