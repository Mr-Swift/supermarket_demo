package com.supermarket.dao.impl;

import com.supermarket.dao.IAccountDao;
import com.supermarket.dao.IObjectMapper;
import com.supermarket.entity.Account;
import com.supermarket.util.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
@SuppressWarnings("all")

public class AccountDaoImpl implements IAccountDao {

    public String getBasicQueryStr() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select");
        stringBuffer.append(" account_id,commodity_name,num,price,pay_check,supplier_name,account_description,time");
        stringBuffer.append(" from tb_account");
        return stringBuffer.toString();
    }

    class AccountObjectMap implements IObjectMapper<Account> {

        @Override
        public Account getObjectFromResultSet(ResultSet resultSet) throws SQLException {
            Account account = new Account();
            account.setAccount_id(resultSet.getInt("account_id"));
            account.setCommodity_name(resultSet.getString("commodity_name"));
            account.setNum(resultSet.getInt("num"));
            account.setPrice(resultSet.getDouble("price"));
            account.setPay_check(resultSet.getString("pay_check"));
            account.setSupplier_name(resultSet.getString("supplier_name"));
            account.setAccount_description(resultSet.getString("account_description"));
            account.setTime(resultSet.getString("time"));

            return account;
        }
    }

    @Override
    public Object listAccounts() throws SQLException {
        return JdbcTemplate.executeQuery(getBasicQueryStr(),new AccountObjectMap(),null);
    }

    @Override
    public Object listByPage(int currentPage, int pageSize) throws SQLException {
        return JdbcTemplate.executeQuery(getBasicQueryStr()+" order by account_id limit ?,?",new AccountObjectMap(),(currentPage-1)*pageSize,pageSize);
    }

    @Override
    public Object listByName(String accountName) throws SQLException {
        return JdbcTemplate.executeQuery(getBasicQueryStr()+" where commodity_name=?",new AccountObjectMap(),accountName);
    }

    @Override
    public Object listByPay(String pay) throws SQLException {
        return JdbcTemplate.executeQuery(getBasicQueryStr()+" where pay_check=?",new AccountObjectMap(),pay);
    }

    @Override
    public Object insertAccount(Account account) throws SQLException {
        return JdbcTemplate.executeUpdate("insert into tb_account(commodity_name,num,price,pay_check,supplier_name,account_description,time) values (?,?,?,?,?,?,?)",account.getCommodity_name(),account.getNum(),account.getPrice(),account.getPay_check(),account.getSupplier_name(),account.getAccount_description(),account.getTime());
    }

    @Override
    public Object modifyAccount(Account account) throws SQLException {
        return JdbcTemplate.executeUpdate("update tb_account set commodity_name=?,num=?,price=?,pay_check=?,supplier_name=?,account_description=?,time=? where account_id=?",account.getCommodity_name(),account.getNum(),account.getPrice(),account.getPay_check(),account.getSupplier_name(),account.getAccount_description(),account.getTime(),account.getAccount_id());
    }

    @Override
    public Object deleteByID(Set setOfId) throws SQLException {
        Boolean checkOfSuccess=true;
        int i=0;
        for(Object obj:setOfId){
            i=JdbcTemplate.executeUpdate("delete from tb_account where account_id=?",obj);
            if(i<0){
                checkOfSuccess=false;
                return checkOfSuccess;
            }else{
                checkOfSuccess=true;
            }
        }
        return checkOfSuccess;
    }

    @Override
    public Object getCountsOfId() throws SQLException {
        List<Integer> list=JdbcTemplate.executeQuery("select count(account_id) from tb_account", new IObjectMapper() {
            @Override
            public Object getObjectFromResultSet(ResultSet resultSet) throws SQLException {
                return resultSet.getInt(1);
            }
        },null);
        return list.get(0);
    }
}
