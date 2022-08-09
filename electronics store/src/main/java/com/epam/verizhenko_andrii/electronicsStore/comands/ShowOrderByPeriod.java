package com.epam.verizhenko_andrii.electronicsStore.comands;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.service.Events;
import com.epam.verizhenko_andrii.electronicsStore.service.OrderService;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class ShowOrderByPeriod implements Commands {
    @Override
    public Events execute(Events event) {
        OrderService order = new OrderService(event.getOrder());
        Scanner sc = new Scanner(System.in);
        System.out.println("enter from date, format dd-MM-yyyy HH:mm:ss");
        String from1 = sc.nextLine();
        System.out.println("enter to date, format dd-MM-yyyy HH:mm:ss");
        String to1 = sc.nextLine();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime from = LocalDateTime.parse(from1, myFormatObj);
        LocalDateTime to = LocalDateTime.parse(to1, myFormatObj);

        for (Map.Entry<LocalDateTime, Map<Product, Integer>> products : order.getOrderByPeriod(from, to).entrySet()) {

            System.out.println(products.getValue());
        }

        return event;
    }
}
