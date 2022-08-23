package com.epam.verizhenko_andrii.electronicsStore.service;

import com.epam.verizhenko_andrii.electronicsStore.DAO.CartDao;
import com.epam.verizhenko_andrii.electronicsStore.DAO.OrderDao;
import com.epam.verizhenko_andrii.electronicsStore.DAO.OrderHistoryDao;
import com.epam.verizhenko_andrii.electronicsStore.DAO.ProductsDao;

public class Events {
    private ProductsDao productsMap;
    private OrderDao order;
    private CartDao cart;
    private OrderHistoryDao history;

    public Events(CartDao cart, ProductsDao products, OrderHistoryDao orderHistory, OrderDao order) {
        this.cart = cart;
        productsMap = products;
        history = orderHistory;
        this.order = order;
    }

    public ProductsDao getProductsMap() {
        return productsMap;
    }

    public void setProductsMap(ProductsDao productMap) {
        this.productsMap = productMap;
    }

    public CartDao getCart() {
        return cart;
    }

    public void setCart(CartDao cart) {
        this.cart = cart;
    }

    public OrderHistoryDao getHistory() {
        return history;
    }

    public void setHistory(OrderHistoryDao history) {
        this.history = history;
    }

    public OrderDao getOrder() {
        return order;
    }

    public void setOrder(OrderDao order) {
        this.order = order;
    }
}
