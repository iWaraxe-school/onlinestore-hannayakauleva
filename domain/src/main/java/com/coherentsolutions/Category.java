package com.coherentsolutions;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private Categories name;
    private List<Product> productList;

    public Category(Categories name) {
        this.name = name;
        this.productList = new ArrayList<>();
    }
}
