package com.epam.verizhenko_andrii.electronicsStore.service;

import com.epam.verizhenko_andrii.electronicsStore.DAO.OrderDao;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.SortedMap;

public class OrderService {
    OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
    public void addOrder(LocalDateTime date, Map<Product, Integer> ord) {
        orderDao.addOrder(date,ord);
    }
    public  Map<Product, Integer> getOrderByDate(LocalDateTime date) {
        return orderDao.getOrderByDate(date);
    }
    public SortedMap<LocalDateTime, Map<Product, Integer>> getOrderByPeriod(LocalDateTime from, LocalDateTime to) {
        return  orderDao.getOrderByPeriod(from,to);
    }
    public OrderDao getOrderDao(){
        return orderDao;
    }
}
