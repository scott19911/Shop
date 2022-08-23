package com.epam.verizhenko_andrii.electronicsStore.service;

import com.epam.verizhenko_andrii.electronicsStore.DAO.OrderHistoryDao;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.LinkedHashMap;

public class OrderHistoryService {
    OrderHistoryDao orderHistory;
    int key = 0;

    public OrderHistoryService(OrderHistoryDao orderHistory) {
        this.orderHistory = orderHistory;
    }

    public LinkedHashMap<Integer, Product> getHistory() {
        return orderHistory.getHistory();
    }

    public void addToHistory(Product product) {
        orderHistory.addToHistory(key, product);
        key++;
    }

    public OrderHistoryDao getOrderHistory() {
        return orderHistory;
    }
}
