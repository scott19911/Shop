package com.epam.verizhenko_andrii.electronicsStore.DAO;

import java.util.Hashtable;

public class CartHashTable implements Cart {
    private Hashtable<String,Integer> cart;
    public CartHashTable() {
        cart = new Hashtable<>();
    }
    @Override
    public Hashtable<String, Integer> getCart() {
        return cart;
    }

    public void setCart(Hashtable<String, Integer> cart) {
        this.cart = cart;
    }

    @Override
    public void clearCart() {
        cart.clear();
    }

    @Override
    public void addToCart(String brand, Integer quantity) {
        cart.put(brand,quantity);
    }
}
