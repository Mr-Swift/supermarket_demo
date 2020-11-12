package com.supermarket.ui.date;

import java.text.SimpleDateFormat;
import java.util.Date;
@SuppressWarnings("all")
public class GetDate {
    public static String get(){
        Date date = new Date();
        //创建SimpleDateFormat对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date);
        return str;
    }
}
