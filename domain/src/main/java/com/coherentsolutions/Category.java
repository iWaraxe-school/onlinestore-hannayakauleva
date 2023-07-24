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
        StringBuilder sb = new StringBuilder("Category: ");
        sb.append(name).append("\n");
        for (var product : productList) {
            sb.append("\t").append(product).append("\n");
        }
        return sb.toString();
    }
}
