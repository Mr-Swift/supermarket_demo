package com.supermarket.service.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.supermarket.dao.IAccountDao;
import com.supermarket.entity.Account;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IAccountService;
import com.supermarket.ts.ITransaction;
import com.supermarket.ts.impl.TransactionImpl;
import com.supermarket.util.DbUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
@SuppressWarnings("all")
public class AccountServiceImpl implements IAccountService {

    private ITransaction itransaction= (ITransaction) ObjectFactory.getObject("ITransaction");
    private IAccountDao iAccountDao = (IAccountDao) ObjectFactory.getObject("IAccountDao");

    @Override
    public Object listAccounts() throws SQLException {
        List<Account> list = null;
        try {
            list = (List<Account>) iAccountDao.listAccounts();
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
    public Object listByName(String accountName) throws SQLException {
        List<Account> list = null;
        try {
            list = (List<Account>) iAccountDao.listByName(accountName);
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
    public Object listByPay(String pay) throws SQLException {
        List<Account> list = null;
        try {
            list = (List<Account>) iAccountDao.listByPay(pay);
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
    public Object insertAccount(Account account) throws SQLException {
        int result = -1;
        try {
            itransaction.begin();
            result = (int) iAccountDao.insertAccount(account);
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
    public Object modifyAccount(Account account) throws SQLException {
        int result = -1;
        try {
            itransaction.begin();
            result = (int) iAccountDao.modifyAccount(account);
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
    public Object deleteByID(Set setOfId) throws SQLException {
    Boolean result = false;
    try {
        itransaction.begin();
        result = (Boolean) iAccountDao.deleteByID(setOfId);
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
    public Object listByPage(int currentPage, int pageSize) throws SQLException {
        List<Account> list = null;
        try {
            list = (List<Account>) iAccountDao.listByPage(currentPage, pageSize);
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
    public Object getCountsOfId() throws SQLException {
        int result=-1;
        try {
            result = (int) iAccountDao.listAccounts();
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
}
