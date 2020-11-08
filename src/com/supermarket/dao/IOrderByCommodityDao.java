package com.supermarket.dao;

import java.sql.SQLException;

@SuppressWarnings("all")
public interface IOrderByCommodityDao <T>{
    public T groupByCommodity() throws SQLException;
}
