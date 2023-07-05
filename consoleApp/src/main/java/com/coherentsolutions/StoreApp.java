package com.coherentsolutions;

public class StoreApp {
    public static void main(String[] args){
        Store store = new Store();
        var pop = new RandomStorePopulator(store);
        System.out.println(store);
    }
}
