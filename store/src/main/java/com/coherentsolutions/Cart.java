package com.coherentsolutions;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Cart {
    // Creating list of products which will be added to the cart
    private final Queue<Product> queue = new ConcurrentLinkedQueue<>();

    //Method adding product to the cart
    public void addProduct(Product product) {
        queue.add(product);
    }
   // method for clearing the cart
    public void clear() {
        queue.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cart:\n");
        for (var queueItem : queue) {
            sb.append("\t").append(queueItem).append("\n");
        }
        return sb.toString();
    }
}
