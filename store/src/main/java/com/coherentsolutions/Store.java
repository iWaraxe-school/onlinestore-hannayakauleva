package com.coherentsolutions;

import java.util.ArrayList;
import java.util.List;

public class Store {

    // added static modificator in order to use this field in static getInstance method
    private static List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

//    public void setCategoryList(List<Category> categoryList) {
//        this.categoryList = categoryList;
//    }

    // Private constructor in order to prevent using it when creating the object and to use getInstance method instead
    private Store() {
    }

    private static Store instance;

    // method that checks if the object of that class already exists. And if no - creates new object and returns it. If yes - returns existing object.
    public static Store getInstance() {
        if (instance == null) {
            System.out.println("Creating...");
            instance = new Store();
            categoryList = new ArrayList<>();
        }
        return instance;
    }


    String storeName = "Bauch-Swaniawski";

//    public Store() {
//        categoryList = new ArrayList<>();
//    }

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
