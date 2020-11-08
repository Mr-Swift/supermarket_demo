package com.supermarket.ui;
@SuppressWarnings("all")

public class CheckOfNull {
    public static boolean check(String str){
        if(str==null || str.length()==0){
            return true;
        }else{
            return false;
        }
    }
}
