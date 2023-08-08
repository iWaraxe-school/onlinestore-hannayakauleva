package com.coherentsolutions;

import com.coherentsolutions.xml.ComparatorSortByMap;
import com.coherentsolutions.xml.XMLParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class StoreApp {
    public static void main(String[] args) throws Exception {

        XMLParser parser = new XMLParser();
        var res = parser.parse();
        System.out.println(res);




        Store store = new Store();
        var pop = new RandomStorePopulator(store);
        System.out.println(store);


        // Created list of all products of all categories
        List<Product> allProducts = new ArrayList<>();
        for (Category category : store.getCategoryList()) {
            allProducts.addAll(category.getProductList());
        }
        // Created new list and populated it with allProducts list in order to update new list
        // while sorting and not to touch the initial list of products
        List<Product> sortedProducts = new ArrayList<>(allProducts);
        Collections.sort(sortedProducts, new ComparatorSortByMap(res));
        System.out.println(sortedProducts);


    }
}
