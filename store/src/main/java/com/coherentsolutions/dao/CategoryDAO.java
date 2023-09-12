package com.coherentsolutions.dao;

import com.coherentsolutions.Category;
import com.coherentsolutions.xml.Sort;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Map;


// All methods of this class are static in order not to instantiate it for using methods
// This DAO classes are kind of helper classes
// Here we encapsulated all category database behavior
public class CategoryDAO {

    // Method creates Categories table in DB
    @SneakyThrows
    static public void createCategoryTable() {
        Connection connection = ConnectionHelper.getInstance().getConnection();
        String query = "create table if not exists categories " +
                "(id int primary key auto_increment," +
                "name varchar(255) not null);";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.execute();
        System.out.println("Table 'categories' is created");
    }

    // Method inserts category into DB
    @SneakyThrows
    public static void insertCategoryDB(Category category) {
        Connection connection = ConnectionHelper.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into categories (name) values(?);");
        statement.setString(1, category.getName().toString());
        // System.out.println(statement);
        statement.execute();
    }

    // Method deletes Categories table
    @SneakyThrows
    static public void dropCategoriesTable() {
        Connection connection = ConnectionHelper.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("drop table if exists categories;");
        statement.execute();
        System.out.println("Table 'Categories' is deleted");
    }

    // Method prints store from db via join
    static public void printStoreJoin() throws SQLException {
        Connection connection = ConnectionHelper.getInstance().getConnection();
        String query = """
                select c.name category_name, p.name product_name, p.rate, p.price from categories c
                join products p on c.id = p.category_id;""";
        PreparedStatement statement = connection.prepareStatement(query);
        // Getting result set from DB
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String categoryName = resultSet.getString("category_name");
            String productName = resultSet.getString("product_name");
            BigDecimal rate = resultSet.getBigDecimal("rate");
            BigDecimal price = resultSet.getBigDecimal("price");
            System.out.printf("Category name: %s, Product name: %s, Product rate: %f, Product price %f \n", categoryName, productName, rate, price);
        }
    }

    //Method populates Store from db using hierarchy
    //I updated all printing methods so that they do not print data into console, but compose data into a string variable and return it as the result
    //Since it is a loop I used StringBuilder in order to avoid a lot of concatenations
    static public String printStoreHierarchy() throws SQLException {
        Connection connection = ConnectionHelper.getInstance().getConnection();
        String query = "select * from categories";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        StringBuilder a = new StringBuilder();
        while (resultSet.next()) {
            String categoryName = resultSet.getString("name");
            int categoryId = resultSet.getInt("id");
            a.append(String.format("Category: %s \n", categoryName));
            // System.out.printf("Category: %s \n", categoryName);
            // Invoking printing products method
            a.append(ProductDAO.printProductsByCategory(categoryId));
        }
        return a.toString();
    }

    static public String printStoreTop5() throws SQLException {
        Connection connection = ConnectionHelper.getInstance().getConnection();

        String query = """
                select c.name category_name, p.name, p.rate, p.price 
                from categories c
                join products p on c.id = p.category_id
                order by price desc limit 5;""";
        System.out.println(query);

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        StringBuilder a = new StringBuilder();
        while (resultSet.next()) {
            String categoryName = resultSet.getString("category_name");
            String productName = resultSet.getString("name");
            BigDecimal rate = resultSet.getBigDecimal("rate");
            BigDecimal price = resultSet.getBigDecimal("price");
            a.append(String.format("\t%s, %s, %.1f, %.2f \n", categoryName, productName, rate, price));
//            System.out.printf("\t%s, %s, %.1f, %.2f \n", categoryName, productName, rate, price);
        }
        return a.toString();
    }

    // Method prints all products sorted according to provided XML file
    static public String printStoreSort(Map<String, Sort> sortRule) throws SQLException {
        Connection connection = ConnectionHelper.getInstance().getConnection();
        // creating SQL query where ordering clause is generating dynamically according to provided XML file
        String query = """
                select c.name category_name, p.name, p.rate, p.price 
                from categories c
                join products p on c.id = p.category_id
                order by    """;
        String queryOrder = " ";
        // iterating through map which was created as a result of parsing XML file
        // and combining ordering clause of query from key and value retrieved from the map
        for (Map.Entry<String, Sort> entry : sortRule.entrySet()) {
            String key = entry.getKey();
            Sort value = entry.getValue();
            queryOrder = queryOrder + key + " " + value.toString() + ", ";
        }
        queryOrder = queryOrder.substring(0, queryOrder.length() - 2);
        query = query + queryOrder;
        System.out.println(query);

        PreparedStatement statement = connection.prepareStatement(query);
        // execute created query
        ResultSet resultSet = statement.executeQuery();
        StringBuilder a = new StringBuilder();
        // iterating through result set of the query and printing sorted store
        while (resultSet.next()) {
            String categoryName = resultSet.getString("category_name");
            String productName = resultSet.getString("name");
            BigDecimal rate = resultSet.getBigDecimal("rate");
            BigDecimal price = resultSet.getBigDecimal("price");
            a.append(String.format("\t%s, %s, %.1f, %.2f \n", categoryName, productName, rate, price));
//            System.out.printf("\t%s, %s, %.1f, %.2f \n", categoryName, productName, rate, price);
        }
        return a.toString();
    }

}
