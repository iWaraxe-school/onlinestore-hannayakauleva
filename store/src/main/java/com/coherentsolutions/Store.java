package com.coherentsolutions;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> categoryList;


    String storeName = "Bauch-Swaniawski";

    public Store() {
        categoryList = new ArrayList<>();
    }

    public void addCategory(Category cat) {
        categoryList.add(cat);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Store: ");
        sb.append(storeName).append("\n");
        for (var category : categoryList) {
            sb.append("\t").append(category);
        }
        return sb.toString();
    }
}
