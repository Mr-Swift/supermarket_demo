package com.supermarket.dao.impl;

import com.supermarket.dao.IObjectMapper;
import com.supermarket.dao.IOrderByCommodityDao;
import com.supermarket.entity.GroupByCommodity;
import com.supermarket.util.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("all")
public class OrderByCommodityDaoImpl implements IOrderByCommodityDao {
    public String getBasicQueryStr() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select t2.commodity_name,t2.sumofnum,t2.sumofprice,t1.supplier_name");
        stringBuffer.append(" from (select commodity_name,supplier_name from tb_account) t1");
        stringBuffer.append(" inner join");
        stringBuffer.append(" (select commodity_name,sum(num) as sumofnum,sum(price) as sumofprice from tb_account group by commodity_name) t2 ");
        stringBuffer.append(" on t1.commodity_name=t2.commodity_name");
        stringBuffer.append(" ORDER BY t2.commodity_name");
        return stringBuffer.toString();
    }

    public String getgetBasicQueryStr_count(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select count(t2.commodity_name)");
        stringBuffer.append(" from (select commodity_name,supplier_name from tb_account) t1");
        stringBuffer.append(" inner join");
        stringBuffer.append(" (select commodity_name,sum(num) as sumofnum,sum(price) as sumofprice from tb_account group by commodity_name) t2 ");
        stringBuffer.append(" on t1.commodity_name=t2.commodity_name");
        stringBuffer.append(" ORDER BY t2.commodity_name");
        return stringBuffer.toString();
    }

    @Override
    public Object groupByCommodity_getCount() throws SQLException {
        List<Integer> list=JdbcTemplate.executeQuery(getgetBasicQueryStr_count(), new IObjectMapper() {
            @Override
            public Object getObjectFromResultSet(ResultSet resultSet) throws SQLException {
                return resultSet.getInt(1);
            }
        },null);
        return list.get(0);
    }

    @Override
    public Object groupByCommodity() throws SQLException {
        List list= JdbcTemplate.executeQuery(getBasicQueryStr(), new IObjectMapper() {
            @Override
            public Object getObjectFromResultSet(ResultSet resultSet) throws SQLException {
                GroupByCommodity groupByCommodity=new GroupByCommodity();
                groupByCommodity.setCommodityName(resultSet.getString(1));
                groupByCommodity.setNum(resultSet.getInt(2));
                groupByCommodity.setPrice(resultSet.getDouble(3));
                groupByCommodity.setSupplierName(resultSet.getString(4));
                return groupByCommodity;
            }
        },null);
        return list;
    }

    @Override
    public Object listByPage(int currentPage, int pageSize) throws SQLException {
        List list= JdbcTemplate.executeQuery(getBasicQueryStr()+" limit ?,?", new IObjectMapper() {
            @Override
            public Object getObjectFromResultSet(ResultSet resultSet) throws SQLException {
                GroupByCommodity groupByCommodity=new GroupByCommodity();
                groupByCommodity.setCommodityName(resultSet.getString(1));
                groupByCommodity.setNum(resultSet.getInt(2));
                groupByCommodity.setPrice(resultSet.getDouble(3));
                groupByCommodity.setSupplierName(resultSet.getString(4));
                return groupByCommodity;
            }
        },(currentPage-1)*pageSize,pageSize);
        return list;
    }
}
