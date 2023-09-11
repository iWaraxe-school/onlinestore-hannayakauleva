package com.coherentsolutions.pages;

import com.coherentsolutions.dao.CategoryDAO;
import com.coherentsolutions.server.ServerHelper;
import com.coherentsolutions.xml.Sort;
import com.coherentsolutions.xml.XMLParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class SortedPage implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {

        String response = "";
        try {
            XMLParser parser = new XMLParser();
            Map<String, Sort> sortRule = parser.parse();
            System.out.println(sortRule);
            response = CategoryDAO.printStoreSort(sortRule);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ServerHelper.handleResponse(exchange, response);
    }


}
