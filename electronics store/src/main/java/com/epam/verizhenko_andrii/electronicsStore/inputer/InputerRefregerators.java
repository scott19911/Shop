package com.epam.verizhenko_andrii.electronicsStore.inputer;

import com.epam.verizhenko_andrii.electronicsStore.products.Refregerators;

import java.util.Scanner;

public class InputerRefregerators<T extends Refregerators> extends InputerMediumDigitalAppliances<T> implements Inputer<T> {
    public T input(T object, Scanner scanner) {
        System.out.println("Enter FreezerVolume");
        object.setFreezerVolume(scanner.nextDouble());
        System.out.println("Enter RefrigeratorVolume");
        object.setRefrigeratorVolume(scanner.nextDouble());
        super.input(object, scanner);
        return object;
    }
}
