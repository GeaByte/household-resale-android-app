package com.example.g1_csis3175_002;

public class OrderModel {
    private int id;
    private int itemID;
    private String date;
    private String status;

    private String itemNames;

    private String shippingAddress;

    private String productImagePath;
    private double price;
    private String typeOfService;




    public OrderModel(int id, int itemName, String date, String status) {
        this.id = id;
        this.itemID = itemID;
        this.date = date;
        this.status = status;
    }

    public OrderModel(int id, String productName, String date, String status,String productImagePath,double price, String typeOfService ) {
        this.id = id;
        this.itemNames = productName;
        this.date = date;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.productImagePath = productImagePath;
        this.price = price;
        this.typeOfService = typeOfService;


    }

    public OrderModel() {

    }



    public int getId() {
        return id;
    }



    public int getItemID() {
        return itemID;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getItemNames() {
        return itemNames;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getProductImagePath() {
        return productImagePath;
    }

    public double getPrice(){return price;}
    public String getTypeOfService(){return typeOfService;}

    public void setPrice(double price){this.price = price;}
    public void setTypeOfService(String typeOfService){this.typeOfService=typeOfService;}

    public void setItemID(int itemID){this.itemID = itemID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
