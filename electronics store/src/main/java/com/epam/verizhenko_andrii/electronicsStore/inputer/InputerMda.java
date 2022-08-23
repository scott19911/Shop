package com.epam.verizhenko_andrii.electronicsStore.inputer;

import com.epam.verizhenko_andrii.electronicsStore.products.MediumDigitalAppliances;

import java.util.Scanner;


public class InputerMda<T extends MediumDigitalAppliances> extends InputerProducts<T> implements Inputer<T> {


    public T input(T object, Scanner scanner) {
        System.out.println("Enter height");
        object.setHeight(scanner.nextDouble());
        System.out.println("Enter width");
        object.setWhidth(scanner.nextDouble());
        System.out.println("Enter weight");
        object.setWeight(scanner.nextDouble());
        super.input(object, scanner);
        return object;
    }
}
