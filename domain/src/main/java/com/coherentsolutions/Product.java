package com.coherentsolutions;

import java.math.BigDecimal;

public class Product {
    // removed final modificator from name, rate and price fields in order to use Builder pattern
    private String name;
    private BigDecimal rate;
    private BigDecimal price;

    // implemented creation of product through Builder pattern
    public static Builder newBuilder() {
        return new Product().new Builder();
    }

    public class Builder {
        private String name;
        private BigDecimal rate;
        private BigDecimal price;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRate(BigDecimal rate) {
            this.rate = rate;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Product build() {
            Product.this.name = this.name;
            Product.this.rate = this.rate;
            Product.this.price = this.price;
            return Product.this;
        }
    }

    public String getName() {
        return name;
    }
    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getPrice() {
        return price;
    }

//    public Product (String name, BigDecimal rate, BigDecimal price) {
//        this.name = name;
//        this.rate = rate;
//        this.price = price;
//    }

    @Override
    public String toString() {
        //Since we used String.format - there is no necessity to use StringBuilder since the concatenation is gone
        String productPrint = String.format("\tProduct: %s, Rate: %.2f, Price: %.2f", name, rate, price);
        return productPrint;
    }
}
