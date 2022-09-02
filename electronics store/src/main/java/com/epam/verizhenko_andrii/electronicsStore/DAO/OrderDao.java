package com.epam.verizhenko_andrii.electronicsStore.DAO;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.SortedMap;

/**
 * A.java
 * Interface class that has the following methods.
 *
 * @author Verizhenko
 * @since 08-14-2022
 */

public interface OrderDao {
    /**
     * add new order
     * @param date - order date
     * @param ord - products map
     */
    void addOrder(LocalDateTime date, Map<Product, Integer> ord);

    /**
     * allows you to get a map of products by order date
     * @param date - order date
     * @return - products list
     */
    Map<Product, Integer> getOrderByDate(LocalDateTime date);

    /**
     * allows you to get a map of products by order date range
     * @param from - start date
     * @param to - end date
     * @return - products list
     */
    SortedMap<LocalDateTime, Map<Product, Integer>> getOrderByPeriod(LocalDateTime from, LocalDateTime to);

}
