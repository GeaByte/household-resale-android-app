package com.example.g1_csis3175_002;

public class UserModel {
    private String fullname;
    private String username;
    private String address;
    private String zipCode;
    private String city;
    private int number;
    private String email;
    private String password;

    public UserModel() {
    }

    public UserModel(String fullname, String username, String address, String zipCode, String city, int number, String email, String password) {
        this.fullname = fullname;
        this.username = username;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.number = number;
        this.email = email;
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public int getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
