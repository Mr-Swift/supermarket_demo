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

    public String getBasicQueryStr_count() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select count(t1.supplier_id)");
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
    public Object OrderBySupplier_getCount() throws SQLException {
        List<Integer> list = JdbcTemplate.executeQuery(getBasicQueryStr_count(), new IObjectMapper() {
            @Override
            public Object getObjectFromResultSet(ResultSet resultSet) throws SQLException {
                return resultSet.getInt(1);
            }
        }, null);
        return list.get(0);
    }

    @Override
    public Object OrderBySupplier() throws SQLException {
        List list= JdbcTemplate.executeQuery(getBasicQueryStr(), new IObjectMapper() {
            @Override
            public Object getObjectFromResultSet(ResultSet resultSet) throws SQLException {
                GroupBySupplier groupBySupplier = new GroupBySupplier();
                groupBySupplier.setSupplierId(resultSet.getInt(1));
                groupBySupplier.setSupplierName(resultSet.getString(2));
                groupBySupplier.setPrice(resultSet.getDouble(3));
                groupBySupplier.setType(resultSet.getInt(4));
                groupBySupplier.setNum(resultSet.getInt(5));
                return groupBySupplier;
            }
        },null);
        return list;
    }


    @Override
    public Object listByPage(int currentPage, int pageSize) throws SQLException {
        List list= JdbcTemplate.executeQuery(getBasicQueryStr()+" limit ?,?", new IObjectMapper() {
            @Override
            public Object getObjectFromResultSet(ResultSet resultSet) throws SQLException {
                GroupBySupplier groupBySupplier = new GroupBySupplier();
                groupBySupplier.setSupplierId(resultSet.getInt(1));
                groupBySupplier.setSupplierName(resultSet.getString(2));
                groupBySupplier.setPrice(resultSet.getDouble(3));
                groupBySupplier.setType(resultSet.getInt(4));
                groupBySupplier.setNum(resultSet.getInt(5));
                return groupBySupplier;
            }
        },(currentPage-1)*pageSize,pageSize);
        return list;
    }
}
