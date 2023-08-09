package com.coherentsolutions;

import com.coherentsolutions.xml.ComparatorSortByMap;
import com.coherentsolutions.xml.Sort;
import com.coherentsolutions.xml.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SortedService {
    private List<Product> unsortedProducts = new ArrayList<>();
    private List<Product> sortedProducts = new ArrayList<>();
    private Map<String, Sort> sortRules;

    public SortedService(Store store) throws ParserConfigurationException, IOException, SAXException {
        //Populating list of all products of all categories
        for (Category category : store.getCategoryList()) {
            unsortedProducts.addAll(category.getProductList());
        }
        //cloning list of products in order not to change initial list of products
        sortedProducts.addAll(unsortedProducts);

        //Parsing xml file and putting their keys and values into the map
        XMLParser parser = new XMLParser();
        sortRules = parser.parse();
    }

    public List<Product> sort() {
        // Created new list and populated it with allProducts list in order to update new list
        // while sorting and not to touch the initial list of products
        Collections.sort(sortedProducts, new ComparatorSortByMap(sortRules));
        return sortedProducts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Sorted Products are:");
        for (var prd : sortedProducts) {
            sb.append("\n").append(prd.toString());
        }
        return sb.toString();
    }
}
