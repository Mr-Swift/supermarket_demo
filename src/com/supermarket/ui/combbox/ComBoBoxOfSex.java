package com.supermarket.ui.combbox;

import javax.swing.*;

public class ComBoBoxOfSex {
    public static JComboBox getComBoBoxOfSex(){
        Object[] datas = new Object[4];
        datas[0]="请选择";
        datas[1]="男";
        datas[2]="女";
        datas[3]="第三性别";
        return new JComboBox(datas);
    }
}
