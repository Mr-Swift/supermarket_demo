package com.supermarket.ui;

import com.supermarket.entity.User;
import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.IUserService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class ComBoxOfAuthority {
    private static IUserService iUserService = (IUserService) ObjectFactory.getObject("IUserService");
    public  static String first="请选择身份";

    public static JComboBox getComBoBoxOfAuthority() throws SQLException {
        List<String> list = (List<String>) iUserService.listAuthority();
        Object[] datas = new Object[1+list.size()];
        datas[0]=ComBoxOfAuthority.first;
        int i=1;
        for (String str: list) {
            datas[i]=str;
            i++;
        }
        JComboBox comboBox=new JComboBox(datas);
        return comboBox;
    }
}
