package com.supermarket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("all")
public interface IObjectMapper<T> {
    public T getObjectFromResultSet(ResultSet resultSet) throws SQLException;
}
