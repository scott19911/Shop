package com.epam.verizhenko_andrii.electronicsStore.controller;

import com.epam.verizhenko_andrii.electronicsStore.DAO.CartDaoImpl;
import com.epam.verizhenko_andrii.electronicsStore.DAO.OrderDaoImpl;
import com.epam.verizhenko_andrii.electronicsStore.DAO.OrderHistoryDaoImpl;
import com.epam.verizhenko_andrii.electronicsStore.DAO.ProductsDao;
import com.epam.verizhenko_andrii.electronicsStore.DAO.ProductsDaoImpl;
import com.epam.verizhenko_andrii.electronicsStore.comands.AddToBucket;
import com.epam.verizhenko_andrii.electronicsStore.comands.BayProductInBucket;
import com.epam.verizhenko_andrii.electronicsStore.comands.Commands;
import com.epam.verizhenko_andrii.electronicsStore.comands.InfoLastBayProduct;
import com.epam.verizhenko_andrii.electronicsStore.comands.MakeOrder;
import com.epam.verizhenko_andrii.electronicsStore.comands.ShowBucket;
import com.epam.verizhenko_andrii.electronicsStore.comands.ShowOrderByDate;
import com.epam.verizhenko_andrii.electronicsStore.comands.ShowOrderByPeriod;
import com.epam.verizhenko_andrii.electronicsStore.comands.ShowProductList;
import com.epam.verizhenko_andrii.electronicsStore.productBase.SelectAddProductsType;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.serializeableProducts.ReadWriteProductsList;
import com.epam.verizhenko_andrii.electronicsStore.server.custom.CustomServer;
import com.epam.verizhenko_andrii.electronicsStore.server.http.HttpServer;
import com.epam.verizhenko_andrii.electronicsStore.service.Events;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Demo {
    private static final String PRODUCTS_FILE_NAME = "products.txt";
    private static final int HELP = 8;
    private static final int EXIT = 10;
    private final static ReadWriteProductsList READ_WRITE_PRODUCTS_LIST = new ReadWriteProductsList();
    private static final Scanner sc = new Scanner(System.in);
    public static Map<Product, Integer> productsMap;
    public static ProductsDao products;
    public static Events events;
    public static HttpServer httpServer = new HttpServer();
    public static SelectAddProductsType addProducts = new SelectAddProductsType();
    static CustomServer customServer = new CustomServer();

    public static void main(String[] args) {
        List<Commands> commandsList = commandsList();
        init();
        new Thread(customServer).start();
        new Thread(httpServer).start();
        int command;
        System.out.println(helpCommands());
        while ((command = sc.nextInt()) < EXIT) {
            if (command == 9) {
                System.out.println("Add products");
                productsMap.putAll(addProducts.addProducts());
            } else if (command == HELP) {
                System.out.println(helpCommands());
            } else {
                events = commandsList.get(command).execute(events);
            }
            System.out.println("Enter command: ");
        }
        CustomServer.closeServer();
        HttpServer.closeServer();
        READ_WRITE_PRODUCTS_LIST.writeToFile(productsMap, PRODUCTS_FILE_NAME);
    }

    static String helpCommands() {
        return "Commands: \n" + "0 - show goods \n" + "1 - add product to cart \n" +
                "2 - bay all products from bucket \n" + "3 - show bucket\n" + "4 - show last five added products\n" +
                "5 - make order\n" + "6 - show order by date\n" + "7 - show order by period\n" +
                "8 - show all commands\n" + "9 - add new product to shop\n" + "10 or more - exit\n" + "Enter command:";
    }

    static void init() {
        products = new ProductsDaoImpl();
        events = new Events(new CartDaoImpl(), new ProductsDaoImpl(), new OrderHistoryDaoImpl(), new OrderDaoImpl() {
        });
        File fileProducts = new File(PRODUCTS_FILE_NAME);

        if (fileProducts.exists()) {
            productsMap = READ_WRITE_PRODUCTS_LIST.readProducts(PRODUCTS_FILE_NAME);
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

    public static Map<Product, Integer> getProductsMap() {
        return productsMap;
    }
}
