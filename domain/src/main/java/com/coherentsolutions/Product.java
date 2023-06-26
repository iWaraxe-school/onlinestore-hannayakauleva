package com.coherentsolutions;

public class Product {
    private String name;
    private double rate;
    private double price;

    public String getName() {
        return name;
    }
    public double getRate() {
        return rate;
    }

    public double getPrice() {
        return price;
    }

    public Product (String name, double rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }
}
