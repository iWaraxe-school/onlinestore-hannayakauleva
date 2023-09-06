package com.coherentsolutions;


import com.coherentsolutions.dao.OrderDAO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CreateOrderTaskDB implements Runnable {
    private final int PROCESSING_TIME = 5;

    // Putting product into order (cart)
    @Override
    @SneakyThrows
    public void run() {
        log.info("The order thread started");
        // generating random number from 1 to 30 as product id
        int randomNum = (int) (Math.random() * 30) + 1;
        // Inserting product with random id to Orders table
        OrderDAO.insertOrderDB(randomNum);
        // sleeping for 5 seconds
        TimeUnit.SECONDS.sleep(PROCESSING_TIME);
        // printing the cart after adding product
        OrderDAO.printOrderDB();
        log.info("The order thread process ended");
    }
}
