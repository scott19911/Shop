package com.epam.verizhenko_andrii.electronicsStore.controller;

import com.epam.verizhenko_andrii.electronicsStore.DAO.*;
import com.epam.verizhenko_andrii.electronicsStore.comands.*;
import com.epam.verizhenko_andrii.electronicsStore.productBase.AddProducts;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.serializeableProducts.ReadWriteProductsList;
import com.epam.verizhenko_andrii.electronicsStore.service.Events;


import java.io.File;
import java.util.*;

public class ShopMain {
    static Map<Product, Integer> productsMap;
    static ProductsDao products;
    static Events events;
    private static final String PRODUCTS_FILE_NAME = "products.txt";
    private static final Scanner SC = new Scanner(System.in);
    private final static ReadWriteProductsList READ_WRITE_PRODUCTS_LIST = new ReadWriteProductsList();

    public static void main(String[] args) {
        List<Commands> commandsList = commandsList();
        init();
        int command;
        System.out.println(helpCommands());
        while ((command = SC.nextInt()) < 9) {
            if (command == 8) {
                System.out.println(helpCommands());
            } else {
                events = commandsList.get(command).execute(events);
            }
            System.out.println("Enter command: ");
        }
        READ_WRITE_PRODUCTS_LIST.writeToFile(productsMap, PRODUCTS_FILE_NAME);
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
        AddProducts addProducts = new AddProducts();
        events = new Events(new CartDaoImpl(), new ProductsDaoImpl(), new OrderHistoryDaoImpl(), new OrderDaoImpl() {
        });
        File fileProducts = new File(PRODUCTS_FILE_NAME);

        if (fileProducts.exists()) {
            productsMap = READ_WRITE_PRODUCTS_LIST.readProducts(PRODUCTS_FILE_NAME);
            System.out.println("Add more products ? 0/1");
            if (SC.nextInt() == 1){
                productsMap.putAll(addProducts.addProducts());
            }
        } else {
            System.out.println("Add products");
            productsMap = new HashMap<>();
            productsMap.putAll(addProducts.addProducts());
        }
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
