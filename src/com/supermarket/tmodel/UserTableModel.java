package com.supermarket.tmodel;


import com.supermarket.entity.User;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IUserService;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("all")
public class UserTableModel extends AbstractTableModel {
    private IUserService iUserService = (IUserService) ObjectFactory.getObject("IUserService");
    private String[] title=null;
    private Object[][] datas=new Object[0][0];

    public UserTableModel(String name) throws SQLException {
        title=new String[]{"编号","姓名","性别","年龄","电话","地址","权限"};
        List<User> list = (List<User>) iUserService.listByUserName(name);
        loadDataToModel(list);
    }

    public UserTableModel() throws SQLException {
        title=new String[]{"编号","姓名","性别","年龄","电话","地址","权限"};
        List<User> list = (List<User>) iUserService.listUsers();
        loadDataToModel(list);
    }

    public UserTableModel(int currentPage,int pageSize) throws SQLException {
        title=new String[]{"编号","姓名","性别","年龄","电话","地址","权限"};
        List<User> list = (List<User>) iUserService.listByPage(currentPage, pageSize);
        loadDataToModel(list);
    }


    public void loadDataToModel(List<User> list){
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][title.length];
            int i = 0 ;
            for(User user :list) {
                datas[i][0]=user.getUser_id();
                datas[i][1] = user.getUser_name();
                datas[i][2]=user.getSex();
                datas[i][3]=user.getAge();
                datas[i][4]=user.getUser_telphone();
                datas[i][5]=user.getUser_address();
                datas[i][6]=user.getAuthority();
                i++;
            }
        }
    }

    @Override
    public int getRowCount() {
        return datas.length;
    }

    @Override
    public int getColumnCount() {
        return title.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datas[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return title[column];
    }
}
