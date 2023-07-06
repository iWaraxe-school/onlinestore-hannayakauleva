package com.coherentsolutions;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final Categories name;

    public Categories getName() {
        return name;
    }

    private List<Product> productList = new ArrayList<>();

    public Category(Categories name) {
        this.name = name;
    }

    //considered encapsulating the addition of products
    public void addProduct(Product product) {
        productList.add(product);
    }

    @Override
    public String toString() {
        String categoryPrint = String.format("Category: name = %s, productList = %s", name, productList);
        return "\nCategory{" + "name=" + name +  ", productList=" + productList + '}';
    }
}
