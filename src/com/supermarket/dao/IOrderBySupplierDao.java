package com.supermarket.dao;

import java.sql.SQLException;

public interface IOrderBySupplierDao <T>{
    public T OrderBySupplier() throws SQLException;
}
