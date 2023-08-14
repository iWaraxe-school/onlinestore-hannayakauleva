package com.coherentsolutions;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class StoreApp {
    public static void main(String[] args) throws Exception {


        //Creating empty store with empty list of categories so far
        Store store = new Store();
        //Populating the store with categories already populated with products
        new RandomStorePopulator(store);
        System.out.println(store);
        StoreService ss = new StoreService(store);

        // Running endless loop for commands entering
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Please enter either sort or top or quit> ");
            String com = in.nextLine();
            // Using switch for processing of entered commands
            // providing case insensitivity of entered text
            switch (com.toLowerCase()) {
                case "quit":
                    System.out.println("quitting...");
                    // Used return instead of break because here we need to exit out of the loop
                    return;
                case "sort":
                    ss.sort();
                    System.out.println(ss);
                    break;
                case "top":
                    System.out.println("Top most expensive products are:");
                    ss.top();
                    break;
                default:
                    System.out.println("You've entered unexpected command. Valid commands are: sort, top, quit");
            }
            // System.out.println("You've just entered a command: " + com);
        }
    }
}
