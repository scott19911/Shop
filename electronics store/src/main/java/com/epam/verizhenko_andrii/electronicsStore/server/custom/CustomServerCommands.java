package com.epam.verizhenko_andrii.electronicsStore.server.custom;

import com.epam.verizhenko_andrii.electronicsStore.controller.Demo;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.Map;
import java.util.Set;

public class CustomServerCommands {
    Map<Product, Integer> productsMaps;

    public CustomServerCommands() {
        productsMaps = Demo.getProductsMap();
    }

    /**
     * Command show products quantity
     *
     * @return - products quantity
     */
    public int getCount() {
        if (productsMaps == null) {
            return 0;
        }
        return productsMaps.size();
    }

    /**
     * Based on the requested product brand, finds its price if didn't find then return error message
     *
     * @param item - requested product brand
     * @return - product brand and its price
     */
    public String getItem(String item) {
        if (productsMaps == null) {
            return "no such product";
        }
        Set<Product> productList = productsMaps.keySet();
        for (Product product : productList
        ) {
            if (product.getBrand() != null && product.getBrand().equalsIgnoreCase(item)) {
                return item + "|" + product.getPrice();
            }
        }
        return "no such product";
    }
}
