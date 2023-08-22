package com.coherentsolutions;

import com.coherentsolutions.categories.BookCategory;
import com.coherentsolutions.categories.FoodCategory;
import com.coherentsolutions.categories.PhoneCategory;

public class CategoryFactory {
    public Category getCategory(Categories cat) {
        switch (cat) {
            case BOOKS:
                return new BookCategory();
            case FOOD:
                return new FoodCategory();
            case PHONES:
                return new PhoneCategory();
            default:
                return null;
        }
    }
}
