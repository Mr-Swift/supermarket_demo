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
        stringBuffer.append(" on t1.commodity_name=t2.commodity_name;");
        return stringBuffer.toString();
    }

    @Override
    public Object groupByCommodity() throws SQLException {
        List list= JdbcTemplate.executeQuery(getBasicQueryStr(), new IObjectMapper() {
            @Override
            public Object getObjectFromResultSet(ResultSet resultSet) throws SQLException {
                GroupByCommodity groupByCommodity=new GroupByCommodity();
                groupByCommodity.setCommodityName(resultSet.getString("t2.commodity_name"));
                groupByCommodity.setNum(resultSet.getInt("t2.sumofnum"));
                groupByCommodity.setPrice(resultSet.getDouble("t2.sumofprice"));
                groupByCommodity.setSupplierName(resultSet.getString("t1.supplier_name"));
                return groupByCommodity;
            }
        },null);
        return list;
    }
}
