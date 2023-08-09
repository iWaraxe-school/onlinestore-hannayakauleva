package com.coherentsolutions.xml;

import com.coherentsolutions.Product;

import java.util.Comparator;
import java.util.Map;

public class ComparatorSortByMap implements Comparator<Product> {
    Map<String, Sort> sorter;


    public ComparatorSortByMap(Map<String, Sort> sorter) {
        this.sorter = sorter;
    }

    @Override
    // method compares two products by all fields and their directions
    public int compare(Product o1, Product o2) {
        for (Map.Entry<String, Sort> entry : sorter.entrySet()) {
            String sortField = entry.getKey();
            Sort sortOrder = entry.getValue();
            int result = sortBySortField(sortField, o1, o2);
            int factor = sortByOrder(sortOrder);
            if (result != 0) {
                return result * factor;
            }
        }
        return 0;
    }

    // method returns calculates factor according to specified sort direction
    private int sortByOrder(Sort order) {
        if (order.equals(Sort.ASC)) return 1;
        else return -1;
        // I removed "else if" because our sortOrder can have only two directions (ASC or DESC)
    }

    // method compares two products by specified field
    private int sortBySortField(String sortField, Product o1, Product o2) {
        switch (sortField) {
            case "name": {
                return o1.getName().compareTo(o2.getName());
            }
            case "price": {
                return o1.getPrice().compareTo(o2.getPrice());
            }
            case "rate": {
                return o1.getRate().compareTo(o2.getRate());
            }
            default:
                return 0;
        }
    }
}
