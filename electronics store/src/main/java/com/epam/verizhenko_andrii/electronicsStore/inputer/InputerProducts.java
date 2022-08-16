package com.epam.verizhenko_andrii.electronicsStore.inputer;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.Scanner;

public class InputerProducts<T extends Product> implements Inputer<T> {

    @Override
    public T inp(T t, Scanner sc) {
        System.out.println("Enter price");
        t.setPrice(sc.nextDouble());
        System.out.println("Enter brand");
        sc.nextLine();
        t.setBrand(sc.nextLine());
        System.out.println("Enter power");
        t.setPower(sc.nextDouble());
        System.out.println("Enter quantity");
        return t;
    }
}
