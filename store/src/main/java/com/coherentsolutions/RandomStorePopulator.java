package com.coherentsolutions;

import org.reflections.Reflections;
import org.w3c.dom.ls.LSOutput;

import java.util.Set;

public class RandomStorePopulator {
    private final Store store;
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

    private void createCategories() throws Exception {
        Reflections reflections = new Reflections("com.coherentsolutions");
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);
        System.out.println(subTypes);


        for (Class<? extends Category> subType : subTypes) {
            try {
                Category category = subType.newInstance();
                populateCategory(category, 5);
                store.addCategory(category);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new Exception(String.format("Couldn't create category: %s", subType.getName()));
                //e.printStackTrace();
            }
        }
    }

}
