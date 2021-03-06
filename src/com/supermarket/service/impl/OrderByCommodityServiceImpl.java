package com.supermarket.service.impl;

import com.supermarket.dao.IAccountDao;
import com.supermarket.dao.IOrderByCommodityDao;
import com.supermarket.entity.Account;
import com.supermarket.entity.GroupByCommodity;
import com.supermarket.entity.User;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IOrderByCommodityService;
import com.supermarket.ts.ITransaction;
import com.supermarket.util.DbUtil;

import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("all")
public class OrderByCommodityServiceImpl implements IOrderByCommodityService{
    private ITransaction itransaction= (ITransaction) ObjectFactory.getObject("ITransaction");
    private IOrderByCommodityDao  iOrderByCommodityDao= (IOrderByCommodityDao) ObjectFactory.getObject("IOrderByCommodityDao");

    @Override
    public Object groupByCommodity_getCount() throws SQLException {
        int result=-1;
        try {
            result= (int) iOrderByCommodityDao.groupByCommodity_getCount();
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
    public Object groupByCommodity() throws SQLException {
        List<GroupByCommodity> list = null;
        try {
            list = (List<GroupByCommodity>) iOrderByCommodityDao.groupByCommodity();
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
        List<GroupByCommodity> list = null;
        try {
            list = (List<GroupByCommodity>) iOrderByCommodityDao.listByPage(currentPage,pageSize);
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
