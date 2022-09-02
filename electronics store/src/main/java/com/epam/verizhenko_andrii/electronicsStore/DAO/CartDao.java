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
    void addToCart(String brand, Integer quantity);

    Hashtable<String, Integer> getCart();

    void setCart(Hashtable<String, Integer> cart);

    void clearCart();

    int alreadyAddBrand(String brand);
}
