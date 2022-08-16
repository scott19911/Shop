package com.epam.verizhenko_andrii.electronicsStore.productBase;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.*;

/**
 * Creating a simple product with generated parameters
 */
public class AddAutogenerateImpl implements Addable {
    @Override
    public Map<Product,Integer> add() {
        Map<Product,Integer>  map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int nProducts = 1;
        while (nProducts > 0){
            Product product = new Product();
            product.setBrand("Фирма " + new Random().nextInt());
            product.setPower(new Random().nextInt(Integer.MAX_VALUE));
            product.setPrice(new Random().nextInt(Integer.MAX_VALUE));
            map.put(product,new Random().nextInt(Integer.MAX_VALUE));
            System.out.println("Add more products 0/1");
            nProducts = sc.nextInt();
        }
        return map;
    }
}
