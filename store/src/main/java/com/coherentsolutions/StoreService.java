package com.coherentsolutions;

import com.coherentsolutions.exceptions.XMLParserException;
import com.coherentsolutions.xml.ComparatorSortByMap;
import com.coherentsolutions.xml.Sort;
import com.coherentsolutions.xml.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class StoreService {
    private final List<Product> unsortedProducts = new ArrayList<>();
    public List<Product> getUnsortedProducts() {
        return Collections.unmodifiableList(unsortedProducts);
    }
    private final List<Product> sortedProducts = new ArrayList<>();

    private final Map<String, Sort> sortRules;

    public StoreService(Store store) throws ParserConfigurationException, IOException, SAXException, XMLParserException {
        //Populating list of all products of all categories
        for (Category category : store.getCategoryList()) {
            unsortedProducts.addAll(category.getProductList());
        }
        //cloning list of products in order not to change initial list of products
        sortedProducts.addAll(unsortedProducts);
        // Put the result of parsing of XML into the map
        sortRules = getFromXML();

    }

    public List<Product> sort() {
        // Created new list and populated it with allProducts list in order to update new list
        // while sorting and not to touch the initial list of products
        Collections.sort(sortedProducts, new ComparatorSortByMap(sortRules));
        return sortedProducts;
    }

    // Transformed list to stream to use java 8 comparator
    public void top() {
        unsortedProducts.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(5)
                .forEach(System.out::println);
    }

    // Separated parsing XML into separate method
    // In Production there can be getFromJSON or getFromDB methods
    //Parsing xml file and putting their keys and values into the map
    private Map<String, Sort> getFromXML() throws ParserConfigurationException, IOException, SAXException, XMLParserException {
        XMLParser parser = new XMLParser();
        return parser.parse();
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
