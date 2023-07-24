package com.coherentsolutions;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> categoryList;

    public Store() {
        categoryList = new ArrayList<>();
    }

    public void addCategory(Category cat) {
        categoryList.add(cat);
    }

    @Override
    public String toString() {
        String storePrint = String.format("Store: categoryList = %s", categoryList);
        return storePrint;
    }
}
