package com.coherentsolutions.server;

import com.coherentsolutions.pages.MainPage;
import com.coherentsolutions.pages.OrderPage;
import com.coherentsolutions.pages.SortedPage;
import com.coherentsolutions.pages.Top5Page;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    static private HttpServer server;
    public static void startServer() throws IOException {
        server = HttpServer.create();
        server.bind(new InetSocketAddress(8088), 0);
        HttpContext contextMainPage = server.createContext("/", new MainPage());
        HttpContext contextOrderPage = server.createContext("/order", new OrderPage());
        HttpContext contextTop5Page = server.createContext("/top5", new Top5Page());
        HttpContext contextSorted = server.createContext("/sort", new SortedPage());

        contextMainPage.setAuthenticator(new Auth("get"));
        contextOrderPage.setAuthenticator(new Auth("get"));
        contextTop5Page.setAuthenticator(new Auth("get"));
        contextSorted.setAuthenticator(new Auth("get"));

        server.setExecutor(null);
        server.start();
    }

    public static void stopServer() {
        server.stop(1);
    }
}
