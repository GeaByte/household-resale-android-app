package com.example.g1_csis3175_002;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductModel implements Serializable, Comparable<ProductModel> {
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
    private String uploadTime;



    private ArrayList<Double> coordinates;
    private Double distance;


    private static final long serialVersionUID = 1L;

    public ProductModel(int productID, String productName, double price,
                        String imagePath, String description, String seller, String pickupAddress, String uploadTime) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;
        this.seller = seller;
        this.pickupAddress = pickupAddress;
        this.uploadTime = uploadTime;
    }

    public ProductModel(int productID, String productName, double price,
                        String imagePath, String description, String seller, String pickupAddress) {
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

    public String getUploadTime() {
        return uploadTime;
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

    public double calculateDistance(LocationHelper lp, double userLatitude, double userLongitude, double itemLatitude, double itemLongitude){
        return lp.calculateDistance(userLatitude, userLongitude, itemLatitude, itemLongitude);
    }

    public ArrayList<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(ProductModel o) {
        return Double.compare(this.distance, o.distance);
    }
}
