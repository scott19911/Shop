package com.epam.verizhenko_andrii.electronicsStore.server.http;

import com.epam.verizhenko_andrii.electronicsStore.controller.Demo;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.Map;
/**
 * Implements get count command
 */
public class GetCountCommand implements Commands {
    Map<Product, Integer> productsMaps;
    /**
     * Product list initialization
     */
    public GetCountCommand() {
        productsMaps = Demo.getProductsMap();
    }

    @Override
    public String execute() {
        if (productsMaps == null) {
            return "{empty}";
        }
        return String.format("{count:%d}", productsMaps.size());
    }
}
