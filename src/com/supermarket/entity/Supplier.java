package com.supermarket.entity;

import java.io.Serializable;

@SuppressWarnings("all")
public class Supplier implements Serializable {
    private int supplier_id;
    private String supplier_name;
    private String supplier_description;
    private String linkman;
    private String supplier_telphone;
    private String supplier_address;

    public Supplier() {
    }

    public Supplier(String supplier_name, String supplier_description, String linkman, String supplier_telphone, String supplier_address) {
        this.supplier_name = supplier_name;
        this.supplier_description = supplier_description;
        this.linkman = linkman;
        this.supplier_telphone = supplier_telphone;
        this.supplier_address = supplier_address;
    }

    public Supplier(int supplier_id, String supplier_name, String supplier_description, String linkman, String supplier_telphone, String supplier_address) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.supplier_description = supplier_description;
        this.linkman = linkman;
        this.supplier_telphone = supplier_telphone;
        this.supplier_address = supplier_address;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_description() {
        return supplier_description;
    }

    public void setSupplier_description(String supplier_description) {
        this.supplier_description = supplier_description;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getSupplier_telphone() {
        return supplier_telphone;
    }

    public void setSupplier_telphone(String supplier_telphone) {
        this.supplier_telphone = supplier_telphone;
    }

    public String getSupplier_address() {
        return supplier_address;
    }

    public void setSupplier_address(String supplier_address) {
        this.supplier_address = supplier_address;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplier_id=" + supplier_id +
                ", supplier_name='" + supplier_name + '\'' +
                ", supplier_description='" + supplier_description + '\'' +
                ", linkman='" + linkman + '\'' +
                ", supplier_telphone='" + supplier_telphone + '\'' +
                ", supplier_address='" + supplier_address + '\'' +
                '}';
    }
}
