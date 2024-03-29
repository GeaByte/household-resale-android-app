package com.example.g1_csis3175_002;

import java.io.Serializable;
public class ProductModel implements Serializable {
    private int productID;
    private String productName;
    private int imgid;

    private double price;

    private String imagePath;

    private String description;

    private int orderId;
    private String itemDetail;
    private String deliveryOption;
    private String orderStatus;
    private String seller;
    private String pickupAddress;

    private static final long serialVersionUID = 1L;


    public ProductModel(int productID, String productName, double price,
                        String imagePath,String description, String seller, String pickupAddress) {
        this.productID = productID;
        this.productName = productName;
        this.imgid = imgid;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;
        this.seller = seller;
        this.pickupAddress = pickupAddress;

    }

    // Constructor
    public ProductModel(int orderId, String itemDetail, String deliveryOption, String orderStatus, String imagePath) {
        this.orderId = orderId;
        this.itemDetail = itemDetail;
        this.deliveryOption = deliveryOption;
        this.orderStatus = orderStatus;
        this.imagePath = imagePath;
    }

    public ProductModel(String productName, double price, String imagePath,
                        String description, String seller, String pickupAddress) {
        this.productName = productName;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;
        this.seller = seller;
        this.pickupAddress = pickupAddress;
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

    public int getProductID() {
        return productID;
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

    public int getOrderId() {return orderId;}

    public String getItemDetail() {return itemDetail;}

    public String getDeliveryOption() { return deliveryOption;}

    public String getOrderStatus() { return orderStatus;}

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }
}
