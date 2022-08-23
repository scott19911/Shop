package com.epam.verizhenko_andrii.electronicsStore.DAO;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.SortedMap;
/**
 *
 * A.java
 * Interface class that has the following methods.
 *
 * @author Verizhenko
 * @since 08-14-2022
 */

public interface OrderDao {
    void addOrder(LocalDateTime date, Map<Product, Integer> ord);

    Map<Product, Integer> getOrderByDate(LocalDateTime date);

    SortedMap<LocalDateTime, Map<Product, Integer>> getOrderByPeriod(LocalDateTime from, LocalDateTime to);

}
