package com.coherentsolutions.dao;

import com.coherentsolutions.Product;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
    // Method creates Products table in DB
    @SneakyThrows
    static public void createProductTable() {
        Connection connection = ConnectionHelper.getInstance().getConnection();
        String query = """
                create table if not exists products
                (id int primary key auto_increment,
                category_id int not null,
                name varchar(255) not null,
                rate decimal(10, 1) not null,
                price decimal(10, 2) not null,
                foreign key(category_id) references categories(id));""";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.execute();
        System.out.println("Table 'products' is created");
    }

    // Method inserts product to Products table in DB
    @SneakyThrows
    public static void insertProductDB(Product product, String catName) {
        Connection connection = ConnectionHelper.getInstance().getConnection();
        String query = """
                insert into products (category_id, name, rate, price)
                select id, ?, ?, ? from categories
                where name = ?;""";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, product.getName());
        statement.setBigDecimal(2, product.getRate());
        statement.setBigDecimal(3, product.getPrice());
        statement.setString(4, catName);
        // System.out.println(statement);
        statement.execute();
    }

    @SneakyThrows
    static public void dropProductsTable() {
        // 1. Drop existing tables
        Connection connection = ConnectionHelper.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("drop table if exists products;");
        statement.execute();
        System.out.println("Table 'Products' is deleted");
    }

    static public String printProductsByCategory(int categoryId) throws SQLException {
        Connection connection = ConnectionHelper.getInstance().getConnection();
        String query = "select name, rate, price from products where category_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, categoryId);
        ResultSet resultSet = statement.executeQuery();
        StringBuilder a = new StringBuilder();
        while (resultSet.next()) {
            String productName = resultSet.getString("name");
            BigDecimal rate = resultSet.getBigDecimal("rate");
            BigDecimal price = resultSet.getBigDecimal("price");
            a.append(String.format("\t%s, %.1f, %.2f \n", productName, rate, price));
            // System.out.printf("\t%s, %.1f, %.2f \n", productName, rate, price);
        }
        return a.toString();
    }
}
