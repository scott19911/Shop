package com.epam.verizhenko_andrii.electronicsStore.comands;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.service.Events;
import com.epam.verizhenko_andrii.electronicsStore.service.ProductsService;


import java.util.Map;

public class ShowProductList implements Commands {
    @Override
    public Events execute(Events event) {
        ProductsService service = new ProductsService(event.getProductsMap());
        System.out.println("Product list: ");
        for (Map.Entry<Product, Integer> productIntegerEntry :service.getProducts().entrySet()) {
            System.out.println(productIntegerEntry);
        }
        return event;
    }
}
