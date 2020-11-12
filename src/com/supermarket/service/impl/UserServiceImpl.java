package com.supermarket.service.impl;

import com.supermarket.dao.IUserDao;
import com.supermarket.entity.Supplier;
import com.supermarket.entity.User;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IUserService;
import com.supermarket.ts.ITransaction;
import com.supermarket.util.DbUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@SuppressWarnings("all")

public class UserServiceImpl implements IUserService {
    private ITransaction itransaction = (ITransaction) ObjectFactory.getObject("ITransaction");
    private IUserDao iUserDao = (IUserDao) ObjectFactory.getObject("IUserDao");

    @Override
    public Object listUsers() throws SQLException {
        List<User> list =null;
        try {
            list = (List<User>) iUserDao.listUsers();
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
    public Object listByUserName(String userName) throws SQLException {
        List<User> list =null;
        try {
            list = (List<User>) iUserDao.listByUserName(userName);
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
        List<User> list =null;
        try {
            list = (List<User>) iUserDao.listById(id);
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
    public Object insertUser(User user) throws SQLException {
        int result=-1;
        try {
            result = (int) iUserDao.insertUser(user);
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
    public Object modifyById(User user) throws SQLException {
        int result=-1;
        try {
            result = (int) modifyById(user);
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
    public Object deleteById(Set setOfId) throws SQLException {
        Boolean result = false;
        try {
            itransaction.begin();
            result = (Boolean) iUserDao.deleteById(setOfId);
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
            result = (int) iUserDao.getCountsOfId();
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
        List<User> list = null;
        try {
            list = (List<User>) iUserDao.listByPage(currentPage, pageSize);
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
    public Object listAuthority() throws SQLException {
        List list = null;
        try {
            list = (List) iUserDao.listAuthority();
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
