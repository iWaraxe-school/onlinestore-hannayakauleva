package com.coherentsolutions;

import java.util.List;


public class StoreApp {
    public static void main(String[] args) throws Exception {


        //Creating empty store with empty list of categories so far
        Store store = new Store();
        //Populating the store with categories already populated with products
        var pop = new RandomStorePopulator(store);
        System.out.println(store);

        StoreService ss = new StoreService(store);
        List<Product> sortedStore = ss.sort();
        System.out.println(ss);
    }
}
