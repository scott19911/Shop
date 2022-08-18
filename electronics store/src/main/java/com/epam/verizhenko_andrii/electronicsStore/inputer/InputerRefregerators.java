package com.epam.verizhenko_andrii.electronicsStore.inputer;

import com.epam.verizhenko_andrii.electronicsStore.products.Refregerators;

import java.util.Scanner;

public class InputerRefregerators<T extends Refregerators> extends InputerMda<T> implements Inputer<T> {
    @Override
    public T inp(T obj, Scanner sc) {
        System.out.println("Enter FreezerVolume");
        obj.setFreezerVolume(sc.nextDouble());
        System.out.println("Enter RefrigeratorVolume");
        obj.setRefrigeratorVolume(sc.nextDouble());
        super.inp(obj, sc);
        return obj;
    }
}
