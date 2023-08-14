package com.coherentsolutions;

import  java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Category {
    public Category(Categories name) {
        this.name = name;
    }

    public Categories getName() {
        return name;
    }

    // Applied Collections.unmodifiable method to productList returned in getter to prevent it from the modification
    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    //considered encapsulating the addition of products
    public void addProduct(Product product) {
        productList.add(product);
    }

    private final Categories name;
    private final List<Product> productList = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Category: ");
        sb.append(name).append("\n");
        for (var product : productList) {
            sb.append("\t").append(product).append("\n");
        }
        return sb.toString();
    }
}
