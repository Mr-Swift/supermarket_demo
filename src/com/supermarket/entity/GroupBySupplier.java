package com.supermarket.entity;

import java.io.Serializable;
@SuppressWarnings("all")
public class GroupBySupplier implements Serializable {
    private int supplierId;
    private String supplierName;
    private double price;
    private int type;
    private int num;

    public GroupBySupplier() {
    }

    public GroupBySupplier(int supplierId, String supplierName, double price, int type, int num) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.price = price;
        this.type = type;
        this.num = num;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "GroupBySupplier{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", num=" + num +
                '}';
    }
}
