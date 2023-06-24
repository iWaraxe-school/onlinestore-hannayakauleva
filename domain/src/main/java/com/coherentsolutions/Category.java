package com.coherentsolutions;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private Categories name;
    private List<Product> productList = new ArrayList<>();

    public Category(Categories name) {
        this.name = name;
    }

    //considered encapsulating the addition of products
    public void addProduct(Product product) {
        productList.add(product);
    }
}
