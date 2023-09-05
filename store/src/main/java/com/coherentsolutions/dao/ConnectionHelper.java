package com.coherentsolutions.dao;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionHelper {
    static Connection CONNECTION;
    static Statement STATEMENT = null;
    static final String URL ="jdbc:mysql://localhost:3306/onlinestore";
    static final String USER = "student";
    static final String PASSWORD = "1234";

    // Private constructor in order to prevent using it when creating the object and to use getInstance method instead
    private ConnectionHelper() {
    }

    private static ConnectionHelper instance;


    // Creation connection to DB using Singleton pattern in order not to multiply database sessions
    // method that checks if the object of that class already exists. And if no - creates new object and returns it. If yes - returns existing object.
    @SneakyThrows
    public static ConnectionHelper getInstance() {
        if (instance == null) {
            System.out.println("Creating connection...");
            instance = new ConnectionHelper();
            CONNECTION = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("connected");
        }
        return instance;
    }

    public Connection getConnection() {
        return CONNECTION;
    }

}






