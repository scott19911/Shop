package com.epam.verizhenko_andrii.electronicsStore.DAO;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderHistoryDaoImpl implements OrderHistoryDao {
    private final LinkedHashMap<Integer, Product> history;

    public OrderHistoryDaoImpl() {
        history = new LinkedHashMap() {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 5;
            }

        };
    }

    public LinkedHashMap<Integer, Product> getHistory() {
        return history;
    }

    public void addToHistory(int key, Product product) {
        history.put(key, product);
    }
}
