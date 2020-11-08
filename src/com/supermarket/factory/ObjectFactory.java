package com.supermarket.factory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")

public class ObjectFactory {
    private final static Map<String,Object> OBJECT_MAP=new HashMap<>();
    static{
        InputStream inputStream=ObjectFactory.class.getClassLoader().getResourceAsStream("object.txt");
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

        String message = null;
        try {
            while (null!=(message= bufferedReader.readLine())){
                String[] str=message.split("=");
                String key = str[0];
                String value = str[1];
                OBJECT_MAP.put(key, Class.forName(value).newInstance());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Object getObject(String key){
        return OBJECT_MAP.get(key);
    }
}
