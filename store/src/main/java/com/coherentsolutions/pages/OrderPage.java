package com.coherentsolutions.pages;

import com.coherentsolutions.dao.OrderDAO;
import com.coherentsolutions.server.ServerHelper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class OrderPage implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        // Order page allows 2 request types - POST and GET
        // So if POST request comes -> we place an order
        // If GET request comes - we return page to browser
        if (exchange.getRequestMethod().equals("POST")) {
            // Read request body and put it into orderedProductInJson variable
            InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
            BufferedReader bufferedReader = new BufferedReader((inputStreamReader));
            String orderedProductInJson = bufferedReader.readLine();
            try {
                // Transforming received request body from String to object in order to retrieve node values from it
                JsonObject obj = (JsonObject) new JsonParser().parse(orderedProductInJson);
                int productId = Integer.valueOf(obj.get("product_id").toString());
                // Inserting product with random id to Orders table
                OrderDAO.insertOrderDB(productId);
                // sleeping for 1 seconds
                TimeUnit.SECONDS.sleep(1);
                // printing the cart after adding product
                // OrderDAO.printOrderDB();
                String response = "You've ordered a product with id = " + productId;
                ServerHelper.handleResponse(exchange, response);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            String response = "";

            try {
                response = OrderDAO.printOrderDB();
                // handled situation when there are no products in the order - the page did not load in browser
                if (response.isBlank()) response = "There is no products in your order right now";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            ServerHelper.handleResponse(exchange, response);

        }
    }
}
