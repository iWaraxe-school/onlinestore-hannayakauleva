package com.coherentsolutions;

import java.math.BigDecimal;

public class Product {
    private final String name;
    private final BigDecimal rate;
    private final BigDecimal price;

    public String getName() {
        return name;
    }
    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product (String name, BigDecimal rate, BigDecimal price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    @Override
    public String toString() {
        //Since we used String.format - there is no necessity to use StringBuilder since the concatenation is gone
        String productPrint = String.format("\nProduct: name = %s, rate = %.2f, price = %.2f", name, rate, price);
        return productPrint;
    }
}
