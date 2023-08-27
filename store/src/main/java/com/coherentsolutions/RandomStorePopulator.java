package com.coherentsolutions;

import com.coherentsolutions.exceptions.CategoryException;
import org.reflections.Reflections;

import java.util.Set;

public class RandomStorePopulator {
    private final Store store;
    private static final int PRODUCTS_COUNT = 10;
    public RandomStorePopulator(Store store) throws Exception {
        this.store = store;
        createCategories();
    }

    private void populateCategory(Category cat, int number) {
        var gen = new RandomProductGenerator();
        for (int i = 0; i < number; i++) {
            var p = gen.generateProduct(cat.getName());
            cat.addProduct(p);
        }

    }
    //Creates list of categories and populates each category with specified number of products using Factory Method pattern
    private void createCategories() throws Exception {
        Categories[] cats = {Categories.BOOKS, Categories.FOOD, Categories.PHONES};
        CategoryFactory categoryFactory = new CategoryFactory();
        for (Categories catType: cats) {
            Category category = categoryFactory.getCategory(catType);
            populateCategory(category, PRODUCTS_COUNT);
            store.addCategory(category);
        }

    }

//Creates list of categories and populates each category with specified number of products using Reflection
    private void createCategoriesUsingReflection() throws Exception {
        Reflections reflections = new Reflections("com.coherentsolutions");
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);


        for (Class<? extends Category> subType : subTypes) {
            try {
                Category category = subType.newInstance();
                populateCategory(category, PRODUCTS_COUNT);
                store.addCategory(category);
            } catch (InstantiationException | IllegalAccessException e) {
                // besides category name we now pass detailed message of real exception happened
                throw new CategoryException(subType.getName(), e.getMessage());
                //e.printStackTrace();
            }
        }
    }

}
