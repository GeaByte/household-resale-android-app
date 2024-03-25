package com.example.g1_csis3175_002;

public class ProductModel {
    private String productName;
    private int imgid;

    private double price;

    private String imagePath;

    private String description;


    public ProductModel(String productName, double price, String imagePath,String description) {
        this.productName = productName;
        this.imgid = imgid;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
