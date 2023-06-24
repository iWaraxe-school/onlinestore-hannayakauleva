package com.coherentsolutions;

import com.github.javafaker.Faker;

public class RandomProductGenerator {
    private Faker faker = new Faker();

    public Product generateProduct(Categories categoryName) {
        return new Product(
                generateName(categoryName),
                generatePrice(),
                generateRate());
    }

    private String generateName(Categories categoryName) {
        switch (categoryName.toString()) {
            case "FOOD": return faker.food().dish();
            case "BOOKS": return faker.book().title();
            default: return null;
        }
    }

    private double generatePrice() {
        return faker.number().randomDouble(2, 0, 100);
    }

    private double generateRate() {
        return faker.number().randomDouble(1, 1, 5);
    }
}
