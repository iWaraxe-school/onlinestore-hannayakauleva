package com.coherentsolutions;

import com.coherentsolutions.dao.OrderDAO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ClearCartTaskDB implements Runnable {
    private final int WAITING_TIME = 120;

    // Clearing cart each 2 minutes
    @SneakyThrows
    @Override
    public void run() {
        log.info("The clear thread started");
        while (true) {
            TimeUnit.SECONDS.sleep(WAITING_TIME);
            OrderDAO.deleteFromOrderTable();
            System.out.println("The cart has just been cleared");
        }
    }
}
