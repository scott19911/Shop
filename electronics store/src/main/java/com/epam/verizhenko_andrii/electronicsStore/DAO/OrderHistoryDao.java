package com.epam.verizhenko_andrii.electronicsStore.DAO;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.LinkedHashMap;

/**
 * A.java
 * Interface class that has the following methods.
 *
 * @author Verizhenko
 * @since 08-14-2022
 */
public interface OrderHistoryDao {
    /**
     * Allows you to get the history of five items added to the cart
     * @return - LinkedHashMap<Integer, Product>
     */
    LinkedHashMap<Integer, Product> getHistory();

    /**
     * add item to history
     * @param key - key to get the value
     * @param product - added item
     */
    void addToHistory(int key, Product product);
}
