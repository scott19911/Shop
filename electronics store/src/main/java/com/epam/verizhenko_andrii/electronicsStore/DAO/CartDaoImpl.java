package com.epam.verizhenko_andrii.electronicsStore.DAO;

import java.util.Hashtable;


public class CartDaoImpl implements CartDao {
    private Hashtable<String, Integer> cart;

    public CartDaoImpl() {
        cart = new Hashtable<>();
    }

    /**
     * Allows you to get a table of orders
     *
     * @return Hashtable<String, Integer>
     */
    @Override
    public Hashtable<String, Integer> getCart() {
        return cart;
    }

    /**
     * Allows you to set the order table
     *
     * @param cart - hashtable of brands products and quantity
     */
    public void setCart(Hashtable<String, Integer> cart) {
        this.cart = cart;
    }

    @Override
    public void clearCart() {
        cart.clear();
    }

    /**
     * Returns the number of goods, if no light is returned -1
     *
     * @param brand - products brand
     * @return - quantity products
     */
    @Override
    public int alreadyAddBrand(String brand) {
        if (cart.get(brand) == null) {
            return -1;
        }
        return cart.get(brand);
    }

    @Override
    public void addToCart(String brand, Integer quantity) {
        cart.put(brand, quantity);
    }
}
