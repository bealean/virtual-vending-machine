package com.techelevator;

import java.math.BigDecimal;

public abstract class Product {
    private String name;
    private BigDecimal price;
    private String location;
    private int quantity;
    private String message;

    public Product(String name, BigDecimal price, String location, String message) {
        this.name = name;
        this.price = price;
        this.location = location;
        this.quantity = 5;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getLocation() {
        return this.location;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void decreasedQuantity() {
        this.quantity--;
    }

    @Override
    public String toString() {
        String newQuantity;
        if (this.quantity == 0) {
            newQuantity = "SOLD OUT";
        } else {
            newQuantity = String.valueOf(this.quantity);
        }
        return this.location + '\t' + this.price + '\t' + newQuantity + '\t' + this.name;}}
