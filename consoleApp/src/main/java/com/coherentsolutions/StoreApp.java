package com.coherentsolutions;

import com.coherentsolutions.xml.XMLParser;


public class StoreApp {
    public static void main(String[] args) throws Exception {

        XMLParser parser = new XMLParser();
        var res = parser.parse();
        System.out.println(res);




//        Store store = new Store();
//        var pop = new RandomStorePopulator(store);
//        System.out.println(store);

    }
}
