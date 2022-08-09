package com.epam.verizhenko_andrii.electronicsStore.comands;

import com.epam.verizhenko_andrii.electronicsStore.service.Events;
import com.epam.verizhenko_andrii.electronicsStore.service.OrderService;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *The class displays all orders on the specified date and time to the console.
 */
public class ShowOrderByDate implements Commands {
    @Override
    public Events execute(Events event) {
        OrderService order = new OrderService(event.getOrder());
        Scanner sc = new Scanner(System.in);
        System.out.println("enter date, format dd-MM-yyyy HH:mm:ss");
        String strinDate = sc.nextLine();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(strinDate, myFormatObj);

        System.out.println(order.getOrderByDate(date));

        return event;
    }
}
