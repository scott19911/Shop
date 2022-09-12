package com.epam.verizhenko_andrii.electronicsStore.productBase;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.Map;

/**
 * Allows you to add new products to the map
 */
public interface AddProduct {
    /**
     *Creates a new product and adds it to the map
     * @return - product map
     */
    Map<Product, Integer> add();

}
