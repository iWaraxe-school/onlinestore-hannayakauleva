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
    public int compare(Product o1, Product o2) {
        for (Map.Entry<String, Sort> entry : sorter.entrySet()) {
            String sortField = entry.getKey();
            Sort sortOrder = entry.getValue();
            int result = sortBySortField(sortField, o1, o2);
            if (result != 0){
                if (sortOrder.equals("ASC")) {
                    return result;
                } else if (sortOrder.equals("DESC")) {
                    return result * -1;
                }
            }
        }
        return 0;
    }

    private int sortBySortField(String sortField, Product o1, Product o2) {
        switch (sortField) {
            case "name": {
                return o1.getName().compareTo(o2.getName());
            }
            case "price": {
                return o1.getPrice().compareTo(o2.getPrice());
            }
            case "rate": {
                return o1.getRate().compareTo(o2.getPrice());
            }
            default: return 0;
        }
    }
}
