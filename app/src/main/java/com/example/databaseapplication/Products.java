package com.example.databaseapplication;

public class Products {
    private int _id;
    private String product_name;

    //constructor of model class that acts as a setter for product name
    public Products(String product_name) {
        this.product_name = product_name;
    }

    //getter for product name
    public String getProduct_name() {
        return product_name;
    }
}
