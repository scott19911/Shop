package com.epam.verizhenko_andrii.electronicsStore.service;

import com.epam.verizhenko_andrii.electronicsStore.DAO.CartDao;

import java.util.Hashtable;

public class CartService {
    CartDao cartDao;

    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public Hashtable<String, Integer> getCart() {
        return cartDao.getCart();
    }

    public void clearCart() {
        cartDao.clearCart();
    }

    public void addToCart(String brand, Integer quantity) {
        cartDao.addToCart(brand, quantity);
    }

    public CartDao getCartDao() {
        return cartDao;
    }

    public int alreadyAddBrand(String brand) {
        return cartDao.alreadyAddBrand(brand);
    }
}
