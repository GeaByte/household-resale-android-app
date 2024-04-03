package com.example.g1_csis3175_002;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private String deliveryAddress;
    private String uploadTime;
    private double latitude;
    private double longitude;
    private Double distance;

    private static final long serialVersionUID = 1L;

    public ProductModel(int productID, String productName, double price,
                        String imagePath, String description, String seller, String pickupAddress, String uploadTime
    , double latitude, double longitude) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;
        this.seller = seller;
        this.pickupAddress = pickupAddress;
        this.uploadTime = uploadTime;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public ProductModel(int productID, String productName, double price, String imagePath,
                        String description, String seller, String pickupAddress, String sellOrShare) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;
        this.seller = seller;
        this.pickupAddress = pickupAddress;
        this.deliveryOption = sellOrShare;
    }

    public String getUploadTime() {
        return uploadTime;
    }
    public ProductModel(int imgid, String productName, double price){
        this.imgid = imgid;
        this.productName = productName;
        this.price = price;
    }
    public ProductModel(String productName, int productID, double price){
        this.productID = productID;
        this.productName = productName;
        this.price = price;
    }

    public ProductModel() {

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
    public void setProductID(int productID) { this.productID = productID; }

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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }


    public String getCurrentDate() {

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String date = dateFormat.format(currentDate);

        return date;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371; // Earth's radius in kilometers
        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Calculate differences between the two points' latitudes and longitudes
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        // Calculate distance using Haversine formula
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distance in kilometers
        return EARTH_RADIUS * c;
    }
}
