package com.epam.verizhenko_andrii.electronicsStore.DAO;

import java.util.Hashtable;

public interface Cart {
   void addToCart(String brand,Integer quantity);
   Hashtable<String,Integer> getCart();
   void setCart(Hashtable<String, Integer> cart);
   void clearCart();

}
