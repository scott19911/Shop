package com.epam.verizhenko_andrii.electronicsStore.productBase;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.Map;
import java.util.Scanner;

public class AddProducts {
    public Map<Product,Integer> addProducts(){
        final Addable addProd;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter type of add: console or auto");
        String type = sc.next();
        addProd = type.equalsIgnoreCase("console")? new AddConsoleImpl():
                type.equalsIgnoreCase("auto")? new AddAutogenerateImpl():null;
        if(addProd == null){
            System.out.println("unsupported command");
            return null;
        }
        return addProd.add();
    }
}
