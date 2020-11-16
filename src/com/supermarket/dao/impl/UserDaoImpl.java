package com.supermarket.dao.impl;

import com.supermarket.dao.IObjectMapper;
import com.supermarket.dao.IUserDao;
import com.supermarket.entity.User;
import com.supermarket.util.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("all")
public class UserDaoImpl implements IUserDao {

    public String getBasicQueryStr() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select");
        stringBuffer.append(" user_id,user_name,password,sex,age,user_telphone,user_address,authority");
        stringBuffer.append(" from tb_user");
        return stringBuffer.toString();
    }

    class UserObjectMap implements IObjectMapper<User>{
        @Override
        public User getObjectFromResultSet(ResultSet resultSet) throws SQLException {
            User user = new User();
            user.setUser_id(resultSet.getInt("user_id"));
            user.setUser_name(resultSet.getString("user_name"));
            user.setPassword(resultSet.getString("password"));
            user.setSex(resultSet.getString("sex"));
            user.setAge(resultSet.getInt("age"));
            user.setUser_telphone(resultSet.getString("user_telphone"));
            user.setUser_address(resultSet.getString("user_address"));
            user.setAuthority(resultSet.getString("authority"));
            return user;
        }
    }

    @Override
    public Object listUsers() throws SQLException {
        return JdbcTemplate.executeQuery(getBasicQueryStr(),new UserObjectMap(),null);
    }

    @Override
    public Object listByUserName(String userName) throws SQLException {
        return JdbcTemplate.executeQuery(getBasicQueryStr()+" where user_name=?",new UserObjectMap(),userName);
    }

    @Override
    public Object listById(int id) throws SQLException {
        return JdbcTemplate.executeQuery(getBasicQueryStr()+" where user_id=?",new UserObjectMap(),id);
    }

    @Override
    public Object insertUser(User user) throws SQLException {
        return JdbcTemplate.executeUpdate("insert into tb_user (user_name,password,sex,age,user_telphone,user_address,authority) values (?,?,?,?,?,?,?)",user.getUser_name(),user.getPassword(),user.getSex(),user.getAge(),user.getUser_telphone(),user.getUser_address(),user.getAuthority());
    }

    @Override
    public Object modifyById(User user) throws SQLException {
        return JdbcTemplate.executeUpdate("update tb_user set user_name=?,password=?,sex=?,age=?,user_telphone=?,user_address=?,authority=? where user_id=?",user.getUser_name(),user.getPassword(),user.getSex(),user.getAge(),user.getUser_telphone(),user.getUser_address(),user.getAuthority(),user.getUser_id());
    }

    @Override
    public Object deleteById(Set set) throws SQLException {
        Boolean checkOfSuccess=true;
        int i=0;
        for(Object obj:set){
            i=JdbcTemplate.executeUpdate("delete from tb_user where user_id=?",obj);
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
        List<Integer> list=JdbcTemplate.executeQuery("select count(user_id) from tb_user", new IObjectMapper() {
            @Override
            public Object getObjectFromResultSet(ResultSet resultSet) throws SQLException {
                return resultSet.getInt(1);
            }
        },null);
        return list.get(0);
    }

    @Override
    public Object listByPage(int currentPage, int pageSize) throws SQLException {
        return JdbcTemplate.executeQuery(getBasicQueryStr()+" order by user_id limit ?,?",new UserObjectMap(),(currentPage-1)*pageSize,pageSize);
    }

    @Override
    public Object listAuthority() throws SQLException {
        List<User> listOfUser=JdbcTemplate.executeQuery("select authority from tb_user group by authority", new IObjectMapper() {
            @Override
            public Object getObjectFromResultSet(ResultSet resultSet) throws SQLException {
                User user = new User(resultSet.getString("authority"));
                return user;
            }
        },null);
        List<String> listOfAuthority=new ArrayList<>();
        for (User user : listOfUser) {
            listOfAuthority.add(user.getAuthority());
        }
        return listOfAuthority;
    }
}
