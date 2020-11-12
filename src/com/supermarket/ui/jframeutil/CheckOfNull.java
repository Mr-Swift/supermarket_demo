package com.supermarket.ui.jframeutil;
@SuppressWarnings("all")

public class CheckOfNull {
    public static boolean check(String str){
//        if(str==null || str.length()==0){
//            return true;
//        }else{
//            return false;
//        }
        if(str==null || str.trim().isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}
