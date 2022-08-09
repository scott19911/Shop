package com.epam.verizhenko_andrii.electronicsStore.DAO;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.SortedMap;


public interface OrderDao {
    void addOrder(LocalDateTime date, Map<Product,Integer> ord);
    Map<Product,Integer> getOrderByDate(LocalDateTime date);
    SortedMap<LocalDateTime, Map<Product,Integer>> getOrderByPeriod(LocalDateTime from, LocalDateTime to);

}
