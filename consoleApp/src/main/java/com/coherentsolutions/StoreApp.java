package com.coherentsolutions;

import com.coherentsolutions.xml.ComparatorSortByMap;
import com.coherentsolutions.xml.XMLParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class StoreApp {
    public static void main(String[] args) throws Exception {


        //Creating empty store with empty list of categories so far
        Store store = new Store();
        //Populating the store with categories already populated with products
        var pop = new RandomStorePopulator(store);
        System.out.println(store);

        SortedService ss = new SortedService(store);
        List<Product> sortedStore = ss.sort();
        System.out.println(ss);
    }
}
