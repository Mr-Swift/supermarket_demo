package com.supermarket.service;

import com.supermarket.entity.Supplier;

import java.sql.SQLException;
import java.util.Set;
@SuppressWarnings("all")
public interface ISupplierService<T> {
    public T listSuppliers() throws SQLException;
    public T listBySupplierName(String supplierName) throws SQLException;
    public T listById(int id);
    public T insertSupplier(Supplier supplier) throws SQLException;
    public T modifySupplier(Supplier supplier) throws SQLException;

    public T deleteSupplier(Set setOfSupplier) throws SQLException;
    public T getCountsOfId() throws SQLException;
    public T listByPage(int currentPage,int pageSize) throws SQLException;

    public T listSupplierNames() throws SQLException;
}
