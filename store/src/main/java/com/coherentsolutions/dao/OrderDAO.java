package com.coherentsolutions.dao;

import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDAO {
    // method creates Orders table
    @SneakyThrows
    static public void createOrdersTable() {
        Connection connection = ConnectionHelper.getInstance().getConnection();
        String query = """
                create table if not exists orders
                (id int primary key auto_increment,
                 product_id int not null,
                 foreign key(product_id) references products(id));""";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.execute();
        System.out.println("Table 'orders' is created");
    }

    // Method inserts product into Order table in DB
    @SneakyThrows
    public static void insertOrderDB(int productId) {
        Connection connection = ConnectionHelper.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into orders (product_id) values(?);");
        statement.setInt(1, productId);
        // System.out.println(statement);
        statement.execute();
    }

    // Method deletes Orders table
    @SneakyThrows
    static public void dropOrderTable() {
        Connection connection = ConnectionHelper.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("drop table if exists orders;");
        statement.execute();
        System.out.println("Table 'Orders' is deleted");
    }

    // Method deletes all data from Orders table
    @SneakyThrows
    static public void deleteFromOrderTable() {
        Connection connection = ConnectionHelper.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from orders;");
        statement.execute();
    }

    @SneakyThrows
    static public void printOrderDB() {
        Connection connection = ConnectionHelper.getInstance().getConnection();
        String query = """
                select c.name category_name, o.product_id, p.name, p.price
                from orders o
                join products p on p.id = o.product_id
                join categories c on c.id = p.category_id;""";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String categoryName = resultSet.getString("category_name");
            int productId = resultSet.getInt("product_id");
            String productName = resultSet.getString("name");
            BigDecimal price = resultSet.getBigDecimal("price");
            System.out.printf("\t%s, %d, %s, %.2f \n", categoryName, productId, productName, price);
        }
    }
}
