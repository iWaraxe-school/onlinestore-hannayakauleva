package com.coherentsolutions;

import com.coherentsolutions.dao.CategoryDAO;
import com.coherentsolutions.dao.OrderDAO;
import com.coherentsolutions.dao.ProductDAO;
import com.coherentsolutions.xml.Sort;
import com.coherentsolutions.xml.XMLParser;

import java.util.Map;
import java.util.Scanner;


public class StoreApp {

    // We refactored adapted this main flow to DB storage instead of memory storage
    public static void main(String[] args) throws Exception {


        //Creating empty store with empty list of categories so far
        //Creating store through singleton pattern
        Store store = Store.getInstance();

        CategoryDAO.createCategoryTable();
        ProductDAO.createProductTable();
        OrderDAO.createOrdersTable();


        //Populating the store with categories already populated with products - currently to DB
        new RandomStorePopulator(store);


        // Creating clear cart task runnable
        ClearCartTaskDB cctd = new ClearCartTaskDB();
        var clearThread = new Thread(cctd);
        //Making the thread Daemon so that it is killed when the main thread is closed
        clearThread.setDaemon(true);
        clearThread.start();

        // Creating order processing task runnable
        CreateOrderTaskDB cotd = new CreateOrderTaskDB();

        Scanner in = new Scanner(System.in);
        // Running endless loop for commands entering
        while (true) {
            System.out.print("Please enter either print, or sort, or top, or order, or quit ");
            String com = in.nextLine();

            // Using switch for processing of entered commands
            // providing case insensitivity of entered text
            switch (com.toLowerCase()) {
                case "quit":
                    OrderDAO.dropOrderTable();
                    ProductDAO.dropProductsTable();
                    CategoryDAO.dropCategoriesTable();
                    System.out.println("quitting...");
                    // Used return instead of break because here we need to exit out of the loop
                    return;
                case "print":
                    // CategoryDAO.printStoreJoin();
                    // Printing store from DB
                    CategoryDAO.printStoreHierarchy();
                    break;
                case "sort":
                    XMLParser parser = new XMLParser();
                    Map<String, Sort> sortRule = parser.parse();
                    System.out.println(sortRule);
                    CategoryDAO.printStoreSort(sortRule);
                    break;
                case "top":
                    CategoryDAO.printStoreTop5();
                    break;
                case "order":
                    new Thread(cotd).start();
                    break;
                default:
                    System.out.println("You've entered unexpected command. Valid commands are: print, sort, top, quit, order");
            }
        }


        // Store stored in memory before DB task
//        public static void oldFlow(){
//            //Creating empty store with empty list of categories so far
//            //Creating store through singleton pattern
//            Store store = Store.getInstance();
//
//            //Populating the store with categories already populated with products
//            new RandomStorePopulator(store);
//            System.out.println(store);
//
//            StoreService ss = new StoreService(store);
//            Cart cart = new Cart();
//
//            // Creating clear cart task runnable
//            ClearCartTask cct = new ClearCartTask(cart);
//            var clearThread = new Thread(cct);
//            //Making the thread Daemon so that it is killed when the main thread is closed
//            clearThread.setDaemon(true);
//            clearThread.start();
//
//            // Creating order processing task runnable
//            CreateOrderTask cot = new CreateOrderTask(ss, cart);
//
//            Scanner in = new Scanner(System.in);
//            // Running endless loop for commands entering
//            while (true) {
//                System.out.print("Please enter either sort or top or quit or order ");
//                String com = in.nextLine();
//
//                // Using switch for processing of entered commands
//                // providing case insensitivity of entered text
//                switch (com.toLowerCase()) {
//                    case "quit":
//                        System.out.println("quitting...");
//                        // Used return instead of break because here we need to exit out of the loop
//                        return;
//                    case "sort":
//                        ss.sort();
//                        System.out.println(ss);
//                        break;
//                    case "top":
//                        System.out.println("Top most expensive products are:");
//                        ss.top();
//                        break;
//                    case "order":
//                        new Thread(cot).start();
//                        break;
//                    default:
//                        System.out.println("You've entered unexpected command. Valid commands are: sort, top, quit, order");
//                }
//            }
//        }
    }
}

