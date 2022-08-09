package com.epam.verizhenko_andrii.electronicsStore.comands;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.service.Events;
import com.epam.verizhenko_andrii.electronicsStore.service.OrderHistoryService;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The class displays the last five items added to the cart.
 */
public class InfoLastBayProduct implements Commands {
    @Override
    public Events execute(Events event) {
        OrderHistoryService history = new OrderHistoryService(event.getHistory());
        LinkedHashMap<Integer, Product> map = history.getHistory();
        System.out.println("Last added goods");
        for (Map.Entry<Integer, Product> product : map.entrySet()) {
            System.out.println(product.getValue());
        }
        return event;
    }
}
