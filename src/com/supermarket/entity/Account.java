package com.supermarket.entity;

import java.io.Serializable;

@SuppressWarnings("all")
public class Account implements Serializable {
    private int account_id;
    private String commodity_name;
    private int num;
    private double price;
    private String pay_check;
    private String supplier_name;
    private String account_description;
    private String time;

    public Account() {
    }

    public Account(String commodity_name, int num, double price, String pay_check, String supplier_name, String account_description, String time) {
        this.commodity_name = commodity_name;
        this.num = num;
        this.price = price;
        this.pay_check = pay_check;
        this.supplier_name = supplier_name;
        this.account_description = account_description;
        this.time = time;
    }

    public Account(int account_id, String commodity_name, int num, double price, String pay_check, String supplier_name, String account_description, String time) {
        this.account_id = account_id;
        this.commodity_name = commodity_name;
        this.num = num;
        this.price = price;
        this.pay_check = pay_check;
        this.supplier_name = supplier_name;
        this.account_description = account_description;
        this.time = time;
    }


    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPay_check() {
        return pay_check;
    }

    public void setPay_check(String pay_check) {
        this.pay_check = pay_check;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getAccount_description() {
        return account_description;
    }

    public void setAccount_description(String account_description) {
        this.account_description = account_description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", commodity_name='" + commodity_name + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", pay_check='" + pay_check + '\'' +
                ", supplier_name='" + supplier_name + '\'' +
                ", account_description='" + account_description + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
