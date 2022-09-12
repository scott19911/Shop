package com.epam.verizhenko_andrii.electronicsStore.inputer;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.Scanner;

/**
 * Input product parameters
 * @param <T> - product type
 */
public class InputerProducts<T extends Product> implements Inputer<T> {

    public T input(T object, Scanner scanner) {
        System.out.println("Enter price");
        object.setPrice(scanner.nextDouble());
        System.out.println("Enter brand");
        scanner.nextLine();
        object.setBrand(scanner.nextLine());
        System.out.println("Enter power");
        object.setPower(scanner.nextDouble());
        System.out.println("Enter quantity");
        return object;
    }
}
