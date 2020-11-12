package com.supermarket.ui.combbox;

import javax.swing.*;

public class ComBoBoxOfPayCheck {

    public static JComboBox getComBoxOfPayCheck(){
        Object[] datas = new Object[3];
        datas[0]="请选择";
        datas[1]="是";
        datas[2]="否";
//        JComboBox jComboBox_CheckOfPay=new JComboBox(datas);
        return new JComboBox(datas);
    }
}
