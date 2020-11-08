package com.supermarket.entity;

import java.io.Serializable;

@SuppressWarnings("all")
public class User implements Serializable {
    private int user_id;
    private String user_name;
    private String password;
    private String sex;
    private int age;
    private String user_telphone;
    private String user_address;
    private String authority;

    public User() {
    }

    public User(String authority) {
        this.authority = authority;
    }

    public User(String user_name, String password, String sex, int age, String user_telphone, String user_address, String authority) {
        this.user_name = user_name;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.user_telphone = user_telphone;
        this.user_address = user_address;
        this.authority = authority;
    }

    public User(String user_name, String password, String authority) {
        this.user_name = user_name;
        this.password = password;
        this.authority = authority;
    }

    public User(int user_id, String user_name, String sex, int age, String user_telphone, String user_address, String authority) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.sex = sex;
        this.age = age;
        this.user_telphone = user_telphone;
        this.user_address = user_address;
        this.authority = authority;
    }

    public User(int user_id, String user_name, String password, String sex, int age, String user_telphone, String user_address, String authority) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.user_telphone = user_telphone;
        this.user_address = user_address;
        this.authority = authority;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUser_telphone() {
        return user_telphone;
    }

    public void setUser_telphone(String user_telphone) {
        this.user_telphone = user_telphone;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", user_telphone='" + user_telphone + '\'' +
                ", user_address='" + user_address + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
