package com.epam.verizhenko_andrii.electronicsStore.inputer;

import com.epam.verizhenko_andrii.electronicsStore.products.Mda;

import java.util.Scanner;


public class InputerMda<T extends Mda> extends InputerProducts<T> implements Inputer<T> {


    @Override
    public T inp(T t, Scanner sc) {
        System.out.println("Enter height");
        t.setHeight(sc.nextDouble());
        System.out.println("Enter width");
        t.setWhidth(sc.nextDouble());
        System.out.println("Enter weight");
        t.setWeight(sc.nextDouble());
        super.inp(t,sc);
        return t;
    }
}
