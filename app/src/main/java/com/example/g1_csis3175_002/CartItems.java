package com.example.g1_csis3175_002;

import java.io.Serializable;

public class CartItems implements Serializable {

    private String productName;
    private String imagePath;
    private double price;

    public CartItems(String imagePath, String productName, double price){
        this.imagePath = imagePath;
        this.productName = productName;
        this.price = price;
    }

    public CartItems(String productName, double price){
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
