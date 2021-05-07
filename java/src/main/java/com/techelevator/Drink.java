package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Product {

    public Drink(String name, BigDecimal price, String location) {
        super(name, price, location, "Glug Glug, Yum!");
    }

}
