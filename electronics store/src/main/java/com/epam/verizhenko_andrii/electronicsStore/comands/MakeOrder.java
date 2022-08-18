package com.epam.verizhenko_andrii.electronicsStore.comands;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.service.CartService;
import com.epam.verizhenko_andrii.electronicsStore.service.Events;
import com.epam.verizhenko_andrii.electronicsStore.service.OrderService;
import com.epam.verizhenko_andrii.electronicsStore.service.ProductsService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Allows you to create an order with the date, product brand and quantity
 */
public class MakeOrder implements Commands {
    @Override
    public Events execute(Events event) {
        OrderService order = new OrderService(event.getOrder());
        Scanner sc = new Scanner(System.in);
        Map<Product, Integer> productsMap = new HashMap<>();
        System.out.println("enter date, format dd-MM-yyyy HH:mm:ss");
        ProductsService products = new ProductsService(event.getProductsMap());
        String strinDate = sc.nextLine();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(strinDate, myFormatObj);
        CartService cart = new CartService(event.getCart());
        for (Map.Entry<String, Integer> stringIntegerEntry : cart.getCart().entrySet()) {
            productsMap.put(products.getProduct(stringIntegerEntry.getKey()), stringIntegerEntry.getValue());
        }
        order.addOrder(date, productsMap);
        event.setOrder(order.getOrderDao());
        return event;
    }
}
