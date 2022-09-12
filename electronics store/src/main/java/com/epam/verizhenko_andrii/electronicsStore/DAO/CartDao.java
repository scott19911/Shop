package com.epam.verizhenko_andrii.electronicsStore.DAO;

import java.util.Hashtable;

/**
 * A.java
 * Interface class that has the following methods.
 *
 * @author Verizhenko
 * @since 08-14-2022
 */
public interface CartDao {
    /**
     * add product to cart
     * @param brand - product brand
     * @param quantity - quantity
     */
    void addToCart(String brand, Integer quantity);

    /**
     *lets get a cart
     * @return -representation of the basket in the form Hashtable
     */
    Hashtable<String, Integer> getCart();

    /**
     * set cart
     * @param cart - new cart
     */
    void setCart(Hashtable<String, Integer> cart);

    /**
     * clears the current cart
     */
    void clearCart();

    /**
     * check there is already added product in the cart
     * @param brand - add products
     * @return - quantity of goods, if missing returns -1
     */
    int alreadyAddBrand(String brand);
}
