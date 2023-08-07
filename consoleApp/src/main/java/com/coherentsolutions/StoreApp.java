package com.coherentsolutions;

import com.coherentsolutions.xml.ComparatorSortByMap;
import com.coherentsolutions.xml.XMLParser;

import java.util.Collections;


public class StoreApp {
    public static void main(String[] args) throws Exception {

        XMLParser parser = new XMLParser();
        var res = parser.parse();
        System.out.println(res);




        Store store = new Store();
        var pop = new RandomStorePopulator(store);

       // Collections.sort(...,new ComparatorSortByMap(res));
        System.out.println(store);

    }
}
