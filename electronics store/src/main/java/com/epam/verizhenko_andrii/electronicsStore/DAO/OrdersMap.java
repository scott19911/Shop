package com.epam.verizhenko_andrii.electronicsStore.DAO;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class OrdersMap implements Order{
    TreeMap<LocalDateTime,Map<Product, Integer>> order;

    public OrdersMap() {
        order = new TreeMap<>();
    }

    @Override
    public void addOrder(LocalDateTime date, Map<Product, Integer> ord) {
        order.put(date,ord);
    }

    @Override
    public  Map<Product, Integer> getOrderByDate(LocalDateTime date) {
        return order.get(date);
    }

    @Override
    public SortedMap<LocalDateTime, Map<Product, Integer>> getOrderByPeriod(LocalDateTime from, LocalDateTime to) {
        return  order.subMap(from,to);
    }

    @Override
    public String toString() {
        return "OrdersMap{" +
                "order=" + order +
                '}';
    }
}
