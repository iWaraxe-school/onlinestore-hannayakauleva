package com.coherentsolutions;

import java.math.BigDecimal;

public class Product {
    private String name;
    private double rate;
    private BigDecimal price;

    public String getName() {
        return name;
    }
    public double getRate() {
        return rate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product (String name, double rate, BigDecimal price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    @Override
    public String toString() {
        return "\nProduct{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", price=" + price +
                '}';
    }
}
