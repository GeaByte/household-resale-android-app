package com.example.g1_csis3175_002;

public class OrderModel {
    private long id;
    private int itemID;
    private String date;
    private String status;

    public OrderModel(long id, int itemName, String date, String status) {
        this.id = id;
        this.itemID = itemID;
        this.date = date;
        this.status = status;
    }

    public long getId() {
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

    public void setItemID(int itemID){this.itemID = itemID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
