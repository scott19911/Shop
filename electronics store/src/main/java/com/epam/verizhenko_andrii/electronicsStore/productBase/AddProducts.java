package com.epam.verizhenko_andrii.electronicsStore.productBase;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.reflectionInputer.ProductServiceAutoGenImpl;

import java.util.Map;
import java.util.Scanner;

public class AddProducts {
    private static final String CONSOLE = "c";
    private static final String AUTOGENERATION = "a";
    private static final String AUTOGENERATION_REFLECTION = "ar";

    public Map<Product, Integer> addProducts() {
        final Addable addProd;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter type of add: c,a,ar");
        String type = sc.next();
        switch (type.toLowerCase()) {
            case CONSOLE:
                addProd = new AddConsoleImpl();
                break;
            case AUTOGENERATION:
                addProd = new AddAutogenerateImpl();
                break;
            case AUTOGENERATION_REFLECTION:
                addProd = new ProductServiceAutoGenImpl();
                break;
            default:
                addProd = null;
        }

        if (addProd == null) {
            System.out.println("unsupported command");
            return null;
        }
        return addProd.add();
    }
}
