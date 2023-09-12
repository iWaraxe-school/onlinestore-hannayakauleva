package com.coherentsolutions.pages;

import com.coherentsolutions.dao.CategoryDAO;
import com.coherentsolutions.server.ServerHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.SQLException;

public class MainPage implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {

        String response = "";
        try {
            response = CategoryDAO.printStoreHierarchy();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ServerHelper.handleResponse(exchange, response);
    }


}
