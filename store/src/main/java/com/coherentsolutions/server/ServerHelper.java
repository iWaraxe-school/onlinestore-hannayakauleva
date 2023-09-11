package com.coherentsolutions.server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ServerHelper {
    static public void handleResponse(HttpExchange exchange, String pageContent) throws IOException {
        var b = pageContent.getBytes();
        exchange.sendResponseHeaders(200, b.length);
        OutputStream os = exchange.getResponseBody();
        os.write(b);
    }
}
