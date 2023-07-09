package com.coherentsolutions.exceptions;

// Created class CategoryException for the exceptions to handle errors during generation and population of certain category
public class CategoryException extends Exception{
    public CategoryException(String catName) {
        super(String.format("Couldn't create category: %s", catName));
    }
}
