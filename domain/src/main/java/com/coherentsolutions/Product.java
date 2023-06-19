package com.coherentsolutions;

public class Product {
    private String name;
    private double rate;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product (String name, double rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }
}