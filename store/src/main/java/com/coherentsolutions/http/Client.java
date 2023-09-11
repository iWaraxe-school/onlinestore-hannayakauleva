package com.coherentsolutions.http;

import com.coherentsolutions.Product;
import com.coherentsolutions.Store;
import com.coherentsolutions.StoreService;
import com.coherentsolutions.exceptions.XMLParserException;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Client {

    public static void clientMakesOrder() {
//        StoreService ss = new StoreService(store);
        int randomNum = (int) (Math.random() * 30) + 1;
//        Product orderedProduct = ss.getUnsortedProducts().get(randomNum);
//        Gson g = new Gson();
        // Since our implementation of order requires just product_id we composed json manually including only one field
        String productInJson = String.format("{\"product_id\":%d}", randomNum);
        // Composing request adjusting request parameters
        RestAssured.baseURI = "http://localhost:8088";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.auth().basic("admin", "admin");
        request.body(productInJson);

        // Executing request (order with random product_id is creating in db)
        Response response = request.post("/order");
        // Printing the response
        System.out.println("The status received: " + response.statusLine());
        System.out.println(("Response body: " + response.getBody().asString()));

    }

}
