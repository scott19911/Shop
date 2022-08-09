package com.epam.verizhenko_andrii.electronicsStore.controller;

import com.epam.verizhenko_andrii.electronicsStore.DAO.*;
import com.epam.verizhenko_andrii.electronicsStore.comands.*;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.service.Events;


import java.util.*;

public class ShopMain {
    static Map<Product, Integer> productsMap;
    static ProductsDao products;
    static Events events;

    public static void main(String[] args) {
        List<Commands> commandsList = commandsList();
        init();
        Scanner sc = new Scanner(System.in);
        int command;
        System.out.println(helpCommands());
        while ((command = sc.nextInt()) < 9) {
            if (command == 8) {
                System.out.println(helpCommands());
            } else {
                events = commandsList.get(command).execute(events);
            }
            System.out.println("Enter command: ");
        }

    }

    static String helpCommands() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commands: \n").append("0 - show goods \n").append("1 - add product to bucket \n");
        sb.append("2 - bay all products from bucket \n").append("3 - show bucket\n").append("4 - show last five added products\n");
        sb.append("5 - make order\n").append("6 - show order by date\n").append("7 - show order by period\n");
        sb.append("8 - show all commands\n").append("9 or more - exit\n").append("Enter command:");
        return sb.toString();
    }

    static void init() {
        products = new ProductsDaoImpl();
        events = new Events(new CartDaoImpl(), new ProductsDaoImpl(), new OrderHistoryDaoImpl(), new OrderDaoImpl() {
        });
        Map<Product, Integer> productsMap = new HashMap<>();
        Product product = new Product("samsung", 200, 125);
        Product product1 = new Product("xiaomi", 100, 40);
        Product product2 = new Product("lg", 150, 120);
        productsMap.put(product, 25);
        productsMap.put(product1, 40);
        productsMap.put(product2, 20);
        products.setProducts(productsMap);
        events.setProductsMap(products);
    }

    static List<Commands> commandsList() {
        List<Commands> commandsList = new ArrayList<>();
        Commands showProductList = new ShowProductList();
        Commands addToBucket = new AddToBucket();
        Commands bayProductInBucket = new BayProductInBucket();
        Commands info = new InfoLastBayProduct();
        Commands makeOrder = new MakeOrder();
        Commands showBucket = new ShowBucket();
        Commands showOrderByDate = new ShowOrderByDate();
        Commands showOrderByPeriod = new ShowOrderByPeriod();
        commandsList.add(showProductList);
        commandsList.add(addToBucket);
        commandsList.add(bayProductInBucket);
        commandsList.add(showBucket);
        commandsList.add(info);
        commandsList.add(makeOrder);
        commandsList.add(showOrderByDate);
        commandsList.add(showOrderByPeriod);
        return commandsList;
    }
}
