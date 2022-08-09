package com.epam.verizhenko_andrii.electronicsStore.comands;



import com.epam.verizhenko_andrii.electronicsStore.service.CartService;
import com.epam.verizhenko_andrii.electronicsStore.service.Events;


import java.util.Map;

/**
 * The class is responsible for purchasing an item from the cart
 * and displaying the total cost of all items on the screen.
 */
public class BayProductInBucket implements Commands {
    @Override
    public Events execute(Events event) {
        CartService cart = new CartService(event.getCart());
        double totalPrice = 0;
        double price;
        String brand;
        for (Map.Entry<String, Integer> stringIntegerEntry : cart.getCart().entrySet()) {
            brand = stringIntegerEntry.getKey();
            price = event.getProductsMap().getProduct(brand).getPrice();
            totalPrice += price * stringIntegerEntry.getValue();
        }
        System.out.println("Total price: " + totalPrice);
        cart.clearCart();
        event.setCart(cart.getCartDao());
        return event;
    }
}
