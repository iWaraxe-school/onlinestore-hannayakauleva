package com.coherentsolutions;

import com.github.javafaker.Faker;

import java.math.BigDecimal;

public class RandomProductGenerator {
    private final Faker faker = new Faker();

    public Product generateProduct(Categories categoryName) {
        return new Product(
                generateName(categoryName),
                generateRate(),
                generatePrice());
    }


    private String generateName(Categories categoryName) {
        switch (categoryName) {
            case FOOD:
                return faker.food().dish();
            case BOOKS:
                return faker.book().title();
            default:
                return null;
        }
    }

    private BigDecimal generatePrice() {
        return BigDecimal.valueOf(faker.number().randomDouble(2, 0, 100));
    }

    private double generateRate() {
        return faker.number().randomDouble(1, 1, 5);
    }
}
