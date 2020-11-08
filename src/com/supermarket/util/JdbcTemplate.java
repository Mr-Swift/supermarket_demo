package com.supermarket.util;

import com.supermarket.dao.IObjectMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("all")
public class JdbcTemplate {
    public static List executeQuery(String sql, IObjectMapper mapper, Object...args) throws SQLException {
        List list=new ArrayList();
        Connection connection=DbUtil.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        setParams(preparedStatement,args);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            list.add(mapper.getObjectFromResultSet(resultSet));
        }
        DbUtil.closeResult(preparedStatement,resultSet);
        return list;
    }

    public static int executeUpdate(String sql,Object...args) throws SQLException {
        int result=-1;
        Connection connection = DbUtil.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        setParams(preparedStatement,args);
        result=preparedStatement.executeUpdate();
        DbUtil.closeResult(preparedStatement,null);
        return result;
    }

    public  static  void setParams(PreparedStatement preparedStatement,Object...args) throws SQLException {


        if(args!=null&&args.length>0){
            for (int i = 0; i < args.length; i++) {
                try {
                    preparedStatement.setObject(i + 1, args[i]);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
