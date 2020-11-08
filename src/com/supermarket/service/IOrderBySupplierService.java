package com.supermarket.service;

import java.sql.SQLException;

public interface IOrderBySupplierService<T> {
    public T OrderBySupplier() throws SQLException;
}
