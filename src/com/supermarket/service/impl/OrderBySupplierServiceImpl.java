package com.supermarket.service.impl;

import com.supermarket.dao.IOrderBySupplierDao;
import com.supermarket.entity.GroupByCommodity;
import com.supermarket.entity.GroupBySupplier;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IOrderBySupplierService;
import com.supermarket.ts.ITransaction;
import com.supermarket.util.DbUtil;

import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("all")
public class OrderBySupplierServiceImpl implements IOrderBySupplierService {

    private ITransaction itransaction= (ITransaction) ObjectFactory.getObject("ITransaction");
    private IOrderBySupplierDao iOrderBySupplierDao= (IOrderBySupplierDao) ObjectFactory.getObject("IOrderBySupplierDao");


    @Override
    public Object OrderBySupplier_getCount() throws SQLException {
        int result=-1;
        try {
            result= (int) iOrderBySupplierDao.OrderBySupplier_getCount();
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
    public Object OrderBySupplier() throws SQLException {
        List<GroupBySupplier> list = null;
        try {
            list = (List<GroupBySupplier>) iOrderBySupplierDao.OrderBySupplier();
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
    public Object listByPage(int currentPage, int pageSize) throws SQLException {
        List<GroupBySupplier> list = null;
        try {
            list = (List<GroupBySupplier>) iOrderBySupplierDao.listByPage(currentPage, pageSize);
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
