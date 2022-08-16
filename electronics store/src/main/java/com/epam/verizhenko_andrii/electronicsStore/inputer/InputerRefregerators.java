package com.epam.verizhenko_andrii.electronicsStore.inputer;
import com.epam.verizhenko_andrii.electronicsStore.products.Refregerators;

import java.util.Scanner;

public class InputerRefregerators<T extends Refregerators> extends InputerMda<T> implements Inputer<T> {
    @Override
    public T inp(T t, Scanner sc) {
        System.out.println("Enter FreezerVolume");
        t.setFreezerVolume(sc.nextDouble());
        System.out.println("Enter RefrigeratorVolume");
        t.setRefrigeratorVolume(sc.nextDouble());
        super.inp(t,sc);
        return t;
    }
}
