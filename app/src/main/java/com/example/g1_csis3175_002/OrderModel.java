package com.example.g1_csis3175_002;

public class OrderModel {
    private long id;
    private String[] itemNames;
    private String date;
    private String status;

    public OrderModel(long id, String[] itemNames, String date, String status) {
        this.id = id;
        this.itemNames = itemNames;
        this.date = date;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String[] getItemNames() {
        return itemNames;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setItemNames(String[] itemNames) {
        this.itemNames = itemNames;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
