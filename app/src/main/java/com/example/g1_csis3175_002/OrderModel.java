package com.example.g1_csis3175_002;

public class OrderModel {
    private int id;
    private String itemNames;
    private String date;
    private String status;
    private String shippingAddress;
    private String productImagePath; // Changed to String for image path

    public OrderModel(int id, String itemNames, String date, String status, String shippingAddress, String productImagePath) {
        this.id = id;
        this.itemNames = itemNames;
        this.date = date;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.productImagePath = productImagePath;
    }

    public OrderModel(int id, String shippingAddress, String date, String status) {
        this.id = id;
        this.itemNames = itemNames;
        this.date = date;
        this.status = status;
        this.shippingAddress = shippingAddress;

    }

    public int getId() {
        return id;
    }

    public String getItemNames() {
        return itemNames;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getProductImagePath() {
        return productImagePath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItemNames(String itemNames) {
        this.itemNames = itemNames;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setProductImagePath(String productImagePath) {
        this.productImagePath = productImagePath;
    }
}
