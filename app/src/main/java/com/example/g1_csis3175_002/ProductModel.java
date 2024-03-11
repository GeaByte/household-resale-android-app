package com.example.g1_csis3175_002;

public class ProductModel {
    private String productName;
    private int imgid;

    private double price;

    public ProductModel(String productName, int imgid, double price) {
        this.productName = productName;
        this.imgid = imgid;
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getProductName() {
        return productName;
    }

    public int getImgid() {
        return imgid;
    }
}
