package com.epam.verizhenko_andrii.electronicsStore.comands;

import com.epam.verizhenko_andrii.electronicsStore.service.CartService;
import com.epam.verizhenko_andrii.electronicsStore.service.Events;

import java.util.Map;

public class ShowBucket implements Commands {
    @Override
    public Events execute(Events event) {
        CartService map = new CartService(event.getCart());
        if (map.getCart().size() == 0) {
            System.out.println("Cart is empty");
            return event;
        }
        System.out.println("Last added goods");
        for (Map.Entry<String, Integer> product : map.getCart().entrySet()) {
            System.out.println(product.getKey() + " " + product.getValue());
        }
        return event;
    }
}
