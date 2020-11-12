package com.supermarket.ui.combbox;

import com.supermarket.factory.ObjectFactory;
import com.supermarket.service.ISupplierService;
import com.supermarket.service.IUserService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;
@SuppressWarnings("all")
public class ComBoBoxOfSupplier {

    private static ISupplierService iSupplierService = (ISupplierService) ObjectFactory.getObject("ISupplierService");
    public  static String first="请选择供应商";

    public static JComboBox getComBoBoxOfSupplier() throws SQLException {
        List<String> list = (List<String>) iSupplierService.listSupplierNames();
        Object[] datas = new Object[1+list.size()];
        datas[0]= ComBoBoxOfSupplier.first;
        System.out.println();
        int i=1;
        for (String str: list) {
            datas[i]=str;
            i++;
        }
        JComboBox comboBox=new JComboBox(datas);
        return comboBox;
    }
}
