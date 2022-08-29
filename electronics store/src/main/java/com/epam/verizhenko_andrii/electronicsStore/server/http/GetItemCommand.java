package com.epam.verizhenko_andrii.electronicsStore.server.http;

import com.epam.verizhenko_andrii.electronicsStore.controller.Demo;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.Map;
import java.util.Set;

public class GetItemCommand implements Commands {
    private final Map<Product, Integer> productsMaps;
    private final String item;

    /**
     * Product list initialization
     *
     * @param item - search product brand
     */
    public GetItemCommand(String item) {
        this.item = item;
        if (Demo.getProductsMap() == null) {
            try {
                Demo.getProductsMap().wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        productsMaps = Demo.getProductsMap();
    }

    @Override
    public String execute() {
        Set<Product> productList = productsMaps.keySet();
        for (Product product : productList
        ) {
            if (product.getBrand() != null && product.getBrand().equalsIgnoreCase(item)) {
                return String.format("{name: %s, price: %.2f}",
                        item, product.getPrice());
            }
        }
        return "{product not found}";
    }
}
