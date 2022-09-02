package com.epam.verizhenko_andrii.electronicsStore.productBase;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import com.epam.verizhenko_andrii.electronicsStore.reflectionInputer.ProductServiceAutoGenImpl;

import java.util.Map;
import java.util.Scanner;

public class SelectAddProductsType {
    private static final String CONSOLE = "c";
    private static final String AUTOGENERATION = "a";
    private static final String AUTOGENERATION_REFLECTION = "ar";

    public Map<Product, Integer> addProducts() {
        final AddProduct addProduct;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter type of add: c,a,ar");
        String type = scanner.next();
        switch (type.toLowerCase()) {
            case CONSOLE:
                addProduct = new AddConsoleImpl();
                break;
            case AUTOGENERATION:
                addProduct = new AddAutogenerateImpl();
                break;
            case AUTOGENERATION_REFLECTION:
                addProduct = new ProductServiceAutoGenImpl();
                break;
            default:
                addProduct = null;
        }
        if (addProduct == null) {
            System.out.println("unsupported command");
            return null;
        }
        return addProduct.add();
    }
}
