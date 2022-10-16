package com.example.electronicshop.cart;

import com.example.electronicshop.products.Product;

import java.util.Map;

/**
 * *
 * product cart view class
 */
public class CartInfo {
    private double totalPrice;
    private int totalQuantity;
    private Map<Product, Integer> cart;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Product, Integer> cart) {
        this.cart = cart;
    }
}
