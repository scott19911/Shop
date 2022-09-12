package com.epam.verizhenko_andrii.electronicsStore.inputer;

import com.epam.verizhenko_andrii.electronicsStore.products.MediumDigitalAppliances;

import java.util.Scanner;
/**
 * Input product parameters
 * @param <T> - product type
 */

public class InputerMediumDigitalAppliances<T extends MediumDigitalAppliances> extends InputerProducts<T> implements Inputer<T> {

    /**
     * Input parameters to instance of MediumDigitalAppliances
     * @param object - instance of MediumDigitalAppliances
     * @param scanner - object for entering information
     * @return - objet with entered parameters
     */
    public T input(T object, Scanner scanner) {
        System.out.println("Enter height");
        object.setHeight(scanner.nextDouble());
        System.out.println("Enter width");
        object.setWidth(scanner.nextDouble());
        System.out.println("Enter weight");
        object.setWeight(scanner.nextDouble());
        super.input(object, scanner);
        return object;
    }
}
