package com.epam.verizhenko_andrii.electronicsStore.comands;

import com.epam.verizhenko_andrii.electronicsStore.DAO.*;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.service.Events;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class ComandsTest {
    Events events;

    @BeforeEach
    void init() {
        ProductsDaoImpl productsMap = new ProductsDaoImpl();
        Map<Product, Integer> products = new HashMap<>();
        Product product = new Product("samsung", 200, 125);
        Product product1 = new Product("xiaomi", 100, 40);
        Product product2 = new Product("lg", 150, 120);
        products.put(product, 25);
        products.put(product1, 40);
        products.put(product2, 20);
        productsMap.setProducts(products);
        events = new Events(new CartDaoImpl(), productsMap, new OrderHistoryDaoImpl(), new OrderDaoImpl());
    }

    @Test
    void addToBucketTest() {
        Commands commands = new AddToBucket();
        int expSizeCart = 1;
        String data = "lg\n1";
        CartDao expCart = new CartDaoImpl();
        expCart.addToCart("lg", 1);

        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        commands.execute(events);
        assertEquals(expSizeCart, events.getCart().getCart().size());
        assertEquals(expCart.getCart(), events.getCart().getCart());
    }

    @Test
    void bayProductTest() {
        Commands commands = new BayProductInBucket();
        int expSizeCart = 0;
        CartDao cartHashTable = new CartDaoImpl();
        cartHashTable.addToCart("lg", 1);
        events.setCart(cartHashTable);
        commands.execute(events);
        assertEquals(expSizeCart, events.getCart().getCart().size());
    }

    @Test
    void showProdutsByDate() {
        Commands commands = new MakeOrder();
        String exp = "{Product{brand=lg, power=150.0, price=120.0}=1}";
        String data = "20-12-2022 14:21:11";
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(data, myFormatObj);
        CartDao cartHashTable = new CartDaoImpl();
        cartHashTable.addToCart("lg", 1);
        events.setCart(cartHashTable);
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        commands.execute(events);
        assertEquals(exp, events.getOrder().getOrderByDate(date).toString());
    }

}