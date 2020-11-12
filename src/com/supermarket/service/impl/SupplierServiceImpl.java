package com.supermarket.service.impl;

import com.supermarket.dao.IAccountDao;
import com.supermarket.dao.ISupplierDao;
import com.supermarket.entity.Account;
import com.supermarket.entity.Supplier;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.ISupplierService;
import com.supermarket.ts.ITransaction;
import com.supermarket.util.DbUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
@SuppressWarnings("all")
public class SupplierServiceImpl implements ISupplierService {
    private ITransaction itransaction= (ITransaction) ObjectFactory.getObject("ITransaction");
    private ISupplierDao iSupplierDao = (ISupplierDao) ObjectFactory.getObject("ISupplierDao");

    @Override
    public Object listSuppliers() throws SQLException {
        List<Supplier> list =null;
        try {
            list = (List<Supplier>) iSupplierDao.listSuppliers();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public Object listSupplierNames() throws SQLException {
        List<String> list =null;
        try {
            list = (List<String>) iSupplierDao.listSupplierNames();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public Object listById(int id) {
        List<Supplier> list = null;
        try {
            list = (List<Supplier>) iSupplierDao.listById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public Object listBySupplierName(String supplierName) throws SQLException {
        List<Supplier> list = null;
        try {
            list = (List<Supplier>) iSupplierDao.listBySupplierName(supplierName);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public Object insertSupplier(Supplier supplier) throws SQLException {
        int result = -1;
        try {
            itransaction.begin();
            result = (int) iSupplierDao.insertSupplier(supplier);
            itransaction.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            itransaction.rollback();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Object modifySupplier(Supplier supplier) throws SQLException {
        int result = -1;
        try {
            itransaction.begin();
            result = (int) iSupplierDao.modifySupplier(supplier);
            itransaction.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            itransaction.rollback();
        } finally {
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Object deleteSupplier(Set setOfSupplier) throws SQLException {
        Boolean result = false;
        try {
            itransaction.begin();
            result = (Boolean) iSupplierDao.deleteSupplier(setOfSupplier);
            itransaction.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            itransaction.rollback();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Object getCountsOfId() throws SQLException {
        int result=-1;
        try {
            result = (int) iSupplierDao.getCountsOfId();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Object listByPage(int currentPage, int pageSize) throws SQLException {
        List<Supplier> list = null;
        try {
            list = (List<Supplier>) iSupplierDao.listByPage(currentPage, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
