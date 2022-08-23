package com.epam.verizhenko_andrii.electronicsStore.productBase;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Creating a simple product with generated parameters
 */
public class AddAutogenerateImpl implements AddProduct {
    @Override
    public Map<Product, Integer> add() {
        Map<Product, Integer> productIntegerMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int flagMoreProduct = 1;
        while (flagMoreProduct > 0) {
            Product product = new Product();
            product.setBrand("Company " + new Random().nextInt());
            product.setPower(new Random().nextInt(Integer.MAX_VALUE));
            product.setPrice(new Random().nextInt(Integer.MAX_VALUE));
            productIntegerMap.put(product, new Random().nextInt(Integer.MAX_VALUE));
            System.out.println("Add more products 0/1");
            flagMoreProduct = scanner.nextInt();
        }
        return productIntegerMap;
    }
}
