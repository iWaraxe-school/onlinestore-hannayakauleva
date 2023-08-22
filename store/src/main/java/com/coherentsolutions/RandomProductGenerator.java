package com.coherentsolutions;

import com.github.javafaker.Faker;

import java.math.BigDecimal;

public class RandomProductGenerator {
    private final Faker faker = new Faker();
    public Product generateProduct(Categories categoryName) {
        // creating of product via Builder pattern
        Product product = Product.newBuilder()
                .setName(generateName(categoryName))
                .setRate(generateRate())
                .setPrice(generatePrice())
                .build();
        return product;

//        return new Product(
//                generateName(categoryName),
//                generateRate(),
//                generatePrice());
    }


    private String generateName(Categories categoryName) {
        switch (categoryName) {
            case FOOD:
                return faker.food().dish();
            case BOOKS:
                return faker.book().title();
            default:
                //I decided to return categoryName + random number if I didn't find appropriate item in Faker library
                var n = faker.number().numberBetween(1, 100);
                var st = categoryName.name();
                var name = st + "_" + n;
                return name;
        }
    }

    private BigDecimal generatePrice() {
        return BigDecimal.valueOf(faker.number().randomDouble(2, 0, 100));
    }

    private BigDecimal generateRate() {
        return BigDecimal.valueOf(faker.number().randomDouble(1, 1, 5));
    }
}
