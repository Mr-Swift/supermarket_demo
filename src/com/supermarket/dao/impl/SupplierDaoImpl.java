package com.supermarket.dao.impl;

import com.supermarket.dao.IObjectMapper;
import com.supermarket.dao.ISupplierDao;
import com.supermarket.entity.Supplier;
import com.supermarket.util.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@SuppressWarnings("all")
public class SupplierDaoImpl implements ISupplierDao {
    public String getBasicQueryStr() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select");
        stringBuffer.append(" supplier_id,supplier_name,supplier_description,linkman,supplier_telphone,supplier_address");
        stringBuffer.append(" from tb_supplier");
        return stringBuffer.toString();
    }

    class SupplierObjectMap implements IObjectMapper<Supplier>{
        @Override
        public Supplier getObjectFromResultSet(ResultSet resultSet) throws SQLException {
            Supplier supplier = new Supplier();
            supplier.setSupplier_id(resultSet.getInt("supplier_id"));
            supplier.setSupplier_name(resultSet.getString("supplier_name"));
            supplier.setSupplier_description(resultSet.getString("supplier_description"));
            supplier.setLinkman(resultSet.getString("linkman"));
            supplier.setSupplier_telphone(resultSet.getString("supplier_telphone"));
            supplier.setSupplier_address(resultSet.getString("supplier_address"));
            return supplier;
        }
    }

    @Override
    public Object listSuppliers() throws SQLException {
        return JdbcTemplate.executeQuery(getBasicQueryStr(),new SupplierObjectMap(),null);
    }

    @Override
    public Object listSupplierNames() throws SQLException {
        List<Supplier> list=JdbcTemplate.executeQuery("select supplier_name from tb_supplier", new IObjectMapper() {
            @Override
            public Object getObjectFromResultSet(ResultSet resultSet) throws SQLException {
                Supplier supplier = new Supplier();
                supplier.setSupplier_name(resultSet.getString("supplier_name"));
                return supplier;
            }
        },null);
        List<String> listOfName=new ArrayList<>();
        for(Object obj:list){
            Supplier supplier= (Supplier) obj;
            listOfName.add(supplier.getSupplier_name());
        }
        return listOfName;
    }

    @Override
    public Object listBySupplierName(String supplierName) throws SQLException {
        return JdbcTemplate.executeQuery(getBasicQueryStr()+" where supplier_name=?",new SupplierObjectMap(),supplierName);
    }

    @Override
    public Object listById(int id) throws SQLException {
        return JdbcTemplate.executeQuery(getBasicQueryStr()+" where supplier_id?",new SupplierObjectMap(),id);
    }

    @Override
    public Object insertSupplier(Supplier supplier) throws SQLException {
        return JdbcTemplate.executeUpdate("insert into tb_supplier (supplier_name,supplier_description,linkman,supplier_telphone,supplier_address) values (?,?,?,?,?)",supplier.getSupplier_name(),supplier.getSupplier_description(),supplier.getLinkman(),supplier.getSupplier_telphone(),supplier.getSupplier_address());
    }

    @Override
    public Object modifySupplier(Supplier supplier) throws SQLException {
        return JdbcTemplate.executeUpdate("update tb_supplier set supplier_name=?,supplier_description=?,linkman=?,supplier_telphone=?,supplier_address=? where supplier_id=?",supplier.getSupplier_name(),supplier.getSupplier_description(),supplier.getLinkman(),supplier.getSupplier_telphone(),supplier.getSupplier_address(),supplier.getSupplier_id());
    }

    @Override
    public Object deleteSupplier(Set setOfSupplier) throws SQLException {
        Boolean checkOfSuccess=true;
        int i=0;
        for(Object obj:setOfSupplier){
            i=JdbcTemplate.executeUpdate("delete from tb_supplier where supplier_id=?",obj);
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
        List<Integer> list=JdbcTemplate.executeQuery("select count(supplier_id) from tb_supplier", new IObjectMapper() {
            @Override
            public Object getObjectFromResultSet(ResultSet resultSet) throws SQLException {
                return resultSet.getInt(1);
            }
        },null);
        return list.get(0);
    }

    @Override
    public Object listByPage(int currentPage, int pageSize) throws SQLException {
        return JdbcTemplate.executeQuery(getBasicQueryStr()+" order by supplier_id limit ?,?",new SupplierObjectMap(),(currentPage-1)*pageSize,pageSize);
    }
}
