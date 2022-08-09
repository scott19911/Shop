package com.epam.verizhenko_andrii.electronicsStore.DAO;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class OrderDaoImpl implements OrderDao {
    TreeMap<LocalDateTime,Map<Product, Integer>> order;

    public OrderDaoImpl() {
        order = new TreeMap<>();
    }

    /**
     * Adds a new order
     * @param date - order creation date
     * @param ord - map products quantity
     */
    @Override
    public void addOrder(LocalDateTime date, Map<Product, Integer> ord) {
        order.put(date,ord);
    }
    /**
     * return order by date
     * @param date - order creation date
     */
    @Override
    public  Map<Product, Integer> getOrderByDate(LocalDateTime date) {
        return order.get(date);
    }
    /**
     * return order by periode
     * @param from - date from
     * @param to - date to
     */
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
