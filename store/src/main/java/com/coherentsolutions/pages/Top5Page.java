package com.coherentsolutions.pages;

import com.coherentsolutions.dao.CategoryDAO;
import com.coherentsolutions.server.ServerHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.SQLException;

public class Top5Page implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {

        String response = "";
        try {
            response = CategoryDAO.printStoreTop5();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ServerHelper.handleResponse(exchange, response);
    }


}
