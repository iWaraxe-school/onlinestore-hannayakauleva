package com.coherentsolutions;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CreateOrderTask implements Runnable {
    private final int PROCESSING_TIME = 5;
    private final StoreService storeService;
    private final Cart cart;


    public CreateOrderTask(StoreService ss, Cart c) {
        storeService = ss;
        cart = c;
    }

    @Override
    @SneakyThrows
    public void run() {
        log.info("The order thread started");
        // generating random number from 0 to 29
        int randomNum = (int) (Math.random() * 30);
        // retrieving the item of random number from the all products in the store collection
        var randomProduct = storeService.getUnsortedProducts().get(randomNum);
        // sleeping for 5 seconds
        TimeUnit.SECONDS.sleep(PROCESSING_TIME);
        // adding Product to the cart
        cart.addProduct(randomProduct);
        // printing the cart after adding product
        System.out.println(cart);
        log.info("The order thread process ended");
    }
}
