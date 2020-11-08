package com.supermarket.dao.impl;

import com.supermarket.dao.IObjectMapper;
import com.supermarket.dao.IOrderBySupplierDao;
import com.supermarket.entity.GroupBySupplier;
import com.supermarket.util.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("all")
public class OrderBySupplierDaoImpl implements IOrderBySupplierDao {
    public String getBasicQueryStr() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select t1.supplier_id,t1.supplier_name,t2.sumofprice,t2.type,t2.sumofnum");
        stringBuffer.append(" from (");
        stringBuffer.append(" select supplier_id,supplier_name");
        stringBuffer.append(" from tb_supplier");
        stringBuffer.append(" order by supplier_id) t1");
        stringBuffer.append(" inner JOIN(");
        stringBuffer.append(" select supplier_name,sum(price) as sumofprice,count(commodity_name) as type,sum(num) as sumofnum");
        stringBuffer.append(" from tb_account");
        stringBuffer.append(" group by supplier_name) t2");
        stringBuffer.append(" on t1.supplier_name=t2.supplier_name");
        stringBuffer.append(" ORDER BY t1.supplier_id");
        return stringBuffer.toString();
    }
    @Override
    public Object OrderBySupplier() throws SQLException {
        List list= JdbcTemplate.executeQuery(getBasicQueryStr(), new IObjectMapper() {
            @Override
            public Object getObjectFromResultSet(ResultSet resultSet) throws SQLException {
                GroupBySupplier groupBySupplier = new GroupBySupplier();
                groupBySupplier.setSupplierId(resultSet.getInt("t1.supplier_id"));
                groupBySupplier.setSupplierName(resultSet.getString("t1.supplier_name"));
                groupBySupplier.setPrice(resultSet.getDouble("t2.sumofprice"));
                groupBySupplier.setType(resultSet.getInt("t2.type"));
                groupBySupplier.setNum(resultSet.getInt("t2.sumofnum"));
                return groupBySupplier;
            }
        },null);
        return list;
    }
}
