package com.aptech.myapp.models;

public class Product {
    private String name;
    private Float price;

    public Product(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
