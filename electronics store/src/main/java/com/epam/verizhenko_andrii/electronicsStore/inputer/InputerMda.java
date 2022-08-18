package com.epam.verizhenko_andrii.electronicsStore.inputer;

import com.epam.verizhenko_andrii.electronicsStore.products.MediumDigitalAppliances;

import java.util.Scanner;


public class InputerMda<T extends MediumDigitalAppliances> extends InputerProducts<T> implements Inputer<T> {


    @Override
    public T inp(T obj, Scanner sc) {
        System.out.println("Enter height");
        obj.setHeight(sc.nextDouble());
        System.out.println("Enter width");
        obj.setWhidth(sc.nextDouble());
        System.out.println("Enter weight");
        obj.setWeight(sc.nextDouble());
        super.inp(obj, sc);
        return obj;
    }
}
