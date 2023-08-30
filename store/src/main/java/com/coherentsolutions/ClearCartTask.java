package com.coherentsolutions;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ClearCartTask implements Runnable {
    private final int WAITING_TIME = 20;
    private final Cart cart;

    public ClearCartTask(Cart cart) {
        this.cart = cart;
    }

    @SneakyThrows
    @Override
    public void run() {
        log.info("The clear thread started");
        while (true) {
            TimeUnit.SECONDS.sleep(WAITING_TIME);
            cart.clear();
            System.out.println("The cart has just been cleared");
        }
    }
}
