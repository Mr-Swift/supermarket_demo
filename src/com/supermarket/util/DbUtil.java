package com.supermarket.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
    private static ThreadLocal<Connection> threadLocal=new ThreadLocal<Connection>();

    public static DataSource dataSource=null;
    public static Properties properties = new Properties();

    static {
        try {
            properties.load(DbUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
        Connection connection= threadLocal.get();
        if(connection==null){
            connection= dataSource.getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }

    public static  void closeConnection() throws SQLException {
        Connection connection= threadLocal.get();
        if(connection!=null){
            connection.close();
        }
        threadLocal.set(null);
    }

    public static void closeResult(PreparedStatement preparedStatement, ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
