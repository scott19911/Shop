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
    LinkedHashMap<Integer, Product> getHistory();

    void addToHistory(int key, Product product);
}
