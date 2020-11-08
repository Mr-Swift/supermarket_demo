package com.supermarket.entity;

import java.io.Serializable;

public class GroupByCommodity implements Serializable {
    private String commodityName;
    private int num;
    private double price;
    private String supplierName;

    public GroupByCommodity() {
    }

    public GroupByCommodity(String commodityName, int num, double price, String supplierName) {
        this.commodityName = commodityName;
        this.num = num;
        this.price = price;
        this.supplierName = supplierName;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "GroupByCommodity{" +
                "commodityName='" + commodityName + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }
}
