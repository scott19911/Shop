package com.example.electronicshop.cart;

import com.example.electronicshop.products.Product;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * *
 * Cart service class
 */
public interface CartService {
   /**
    * allows you to install a cart
    *
    * @param cart - new cart
    */
   void setCart(Map<Product, Integer> cart);

   /**
    * allows you to get a quantity of products in the shopping cart
    *
    * @return - quantity of products
    */
   int totalQuantity();

   /**
    * returns the total price of the cart
    *
    * @return - total price of the cart
    */
   double cartPrice();

   /**
    * updates the contents of the cart
    *
    * @param request parameter to update Cart
    */
   void updateCart(HttpServletRequest request);

   /**
    * allows you to empty the shopping cart
    *
    * @param request - to empty the shopping cart
    */
   void clearCart(HttpServletRequest request);

   /**
    * allows you to get the shopping cart
    *
    * @return - cart
    */
   Map<Product, Integer> getCart();

   /**
    * Generates a presentation class for a shopping cart
    *
    * @return - product cart view class
    */
   CartInfo getCartInfo();
}
