package com.coherentsolutions.exceptions;

// Created class CategoryException for the exceptions to handle errors during generation and population of certain category
public class CategoryException extends Exception{
    // In this exception we show category where error happened and now can also pass detailed message
     public CategoryException(String catName, String details) {
        super(String.format("Couldn't create category: %s\n %s", catName, details));
    }
}
